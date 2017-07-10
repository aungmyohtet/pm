package com.aungmyohtet.pm.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.BoardService;
import com.aungmyohtet.pm.service.OrganizationMemberService;
import com.aungmyohtet.pm.service.OrganizationService;
import com.aungmyohtet.pm.service.ProjectService;
import com.aungmyohtet.pm.service.ResourceService;
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

    @Autowired
    private ResourceService resourceService;

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

    @RequestMapping(value = "/json/{organizationName}/members", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getOrganizationMembers(@PathVariable("organizationName") String organizationName, Model model) {
        /*
         * List<User> users = new ArrayList<>(); List<OrganizationMember> members =
         * organizationService.findMembersByOrganization(id); for (OrganizationMember member : members) {
         * users.add(member.getUser()); }
         */
        List<User> users = userService.findMembersOfOrganization(organizationName);
        return users.stream().map(user -> userService.converToDto(user)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{organizationName}/members", method = RequestMethod.GET)
    public String showOrganizationMembers(@PathVariable("organizationName") String organizationName, Model model) {
        List<User> users = userService.findMembersOfOrganization(organizationName);
        List<UserDto> userDtos = users.stream().map(user -> userService.converToDto(user)).collect(Collectors.toList());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("members", userDtos);
        return "organizationMembers";
    }

    @RequestMapping(value = "/{organizationName}/members/new", method = RequestMethod.GET)
    public String showOrganizationMemberForm(@PathVariable("organizationName") String organizationName, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationName", organizationName);
        return "organizationMemberForm";
    }

    @RequestMapping(value = "/{organizationName}/members/new", method = RequestMethod.POST)
    public String addMemberToOrganization(@PathVariable("organizationName") String organizationName, Model model, @Validated @ModelAttribute User user, BindingResult result) {
        if (userService.findByEmail(user.getEmail()) == null) {
            result.rejectValue("email", "user.email", "Email does not exist.");
            return "organizationMemberForm";
        }

        User users = userService.findMembersOfOrganization(organizationName, user.getEmail());
        if (users != null) {
            result.rejectValue("email", "user.email", "User already exist in this organization.");
            return "organizationMemberForm";
        }
        organizationMemberService.addMemberToOrganization(user.getEmail(), organizationName);
        return "redirect:/" + organizationName + "/members";
    }

    @RequestMapping(value = "/json/organizations", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationDto> list(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<Organization> organizations = userService.findOrganizationsByUser(email);
        return organizations.stream().map(organization -> organizationService.convertToDto(organization)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String showOrganizations(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        model.addAttribute("ownedOrganizations", userService.findOrganizationsCreatedByUser(email));
        model.addAttribute("involvedOrganizations", userService.findOrganizationsInvolvingUser(email));
        return "organizations";
    }

    @RequestMapping(value = "/{organizationName}", method = RequestMethod.GET)
    public String showOrganizationName(@PathVariable("organizationName") String organizationName, Model model) {
        model.addAttribute("page_title", organizationName);
        return "organizationHome";
    }

    @RequestMapping(value = "/organizations/my", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationDto> getOwnedOrganizations(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<Organization> organizations = userService.findOrganizationsCreatedByUser(email);
        return organizations.stream().map(organization -> organizationService.convertToDto(organization)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/organizations/in", method = RequestMethod.GET)
    @ResponseBody
    public List<OrganizationDto> getInvolvedOrganizations(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<Organization> organizations = userService.findOrganizationsInvolvingUser(email);
        return organizations.stream().map(organization -> organizationService.convertToDto(organization)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/json/{organizationName}/projects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectDto> getOrganizationProjects(@PathVariable("organizationName") String organizationName, Model model) {
        List<Project> projects = organizationService.findProjectsByOrganization(organizationName);
        return projects.stream().map(project -> projectService.converToDto(project)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{organizationName}/projects", method = RequestMethod.GET)
    public String showOrganizationProjects(@PathVariable("organizationName") String organizationName, Model model) {
        List<Project> projects = organizationService.findProjectsByOrganization(organizationName);
        List<ProjectDto> projectDtos = projects.stream().map(project -> projectService.converToDto(project)).collect(Collectors.toList());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projects", projectDtos);
        return "projectList";
    }

    @RequestMapping(value = "/{organizationName}/boards", method = RequestMethod.GET)
    public String showOrganizationBoards(@PathVariable("organizationName") String organizationName, Model model) {
        List<Board> allBoards = organizationService.findBoardsByOrganization(organizationName);
        List<Board> boards = new ArrayList<>();

        for (Board board : allBoards) {
            if (board.getStartShownDate().before(Calendar.getInstance().getTime()) && board.getLastShownDate().after(Calendar.getInstance().getTime())) {
                boards.add(board);
            }
        }

        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boards", boards);
        return "organizationBoards";
    }

    @RequestMapping(value = "/{organizationName}/resources", method = RequestMethod.GET)
    public String showOrganizationResources(@PathVariable("organizationName") String organizationName, Model model) {

        Organization organization = organizationService.findByName(organizationName);
        List<Resource> resources = resourceService.findResourceByOrganizationId(organization.getId());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("resources", resources);
        return "organizationResourceList";
    }
}
