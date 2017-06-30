package com.aungmyohtet.pm.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aungmyohtet.pm.dto.OrganizationDto;
import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.OrganizationMemberService;
import com.aungmyohtet.pm.service.OrganizationService;
import com.aungmyohtet.pm.service.ProjectService;
import com.aungmyohtet.pm.service.UserService;

@Controller
public class OrganizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Autowired
    private OrganizationService organizationService;

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/organizations/new", method = RequestMethod.GET)
    public String showOrganizationForm(Model model) {
        model.addAttribute("organization", new Organization());
        return "organizationForm";
    }

    @RequestMapping(value = "/organizations/new", method = RequestMethod.POST)
    public String addOrganization(@Validated @ModelAttribute Organization organization, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "organizationForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        organizationService.addByUser(organization, email);
        return "redirect:/organizations";
    }

    @RequestMapping(value = "/organizations/{id}/members", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> showOrganizationMembers(@PathVariable("id") int id, Model model)
    {
        List<User> users = new ArrayList<>();
        List<OrganizationMember> members = organizationService.findMembersByOrganization(id);
        for (OrganizationMember member : members) {
            users.add(member.getUser());
        }
        return users.stream().map(user -> userService.converToDto(user)).collect(Collectors.toList());
    }
    
    @RequestMapping(value = "/organizations/{id}/members/new", method = RequestMethod.GET)
    public String showOrganizationMemberForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationId", id);
        return "organizationMemberForm";
    }
    
    @RequestMapping(value = "/organizations/{id}/members/new", method = RequestMethod.POST)
    public String addMemberToOrganization(@PathVariable("id") int id, Model model, @ModelAttribute User user) {
        System.out.println("user email is " + user.getEmail());
        organizationMemberService.addMemberToOrganization(user.getEmail(), id);
        return "redirect:/organizations/" + id + "/members";
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationDto> list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<Organization> organizations = userService.findOrganizationsByUser(email);
        return organizations.stream().map(organization -> organizationService.convertToDto(organization)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/organizations/{id}/projects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectDto> showOrganizationProjects(@PathVariable("id") int id, Model model)
    {
        List<Project> projects = organizationService.findProjectsByOrganization(id);
        return projects.stream().map(project -> projectService.converToDto(project)).collect(Collectors.toList());
    }
}
