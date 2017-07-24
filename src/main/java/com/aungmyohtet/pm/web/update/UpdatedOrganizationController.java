package com.aungmyohtet.pm.web.update;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.OrganizationService;
import com.aungmyohtet.pm.service.update.ProjectService;
import com.aungmyohtet.pm.service.update.UserService;

@Controller
public class UpdatedOrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/u/organizations/new")
    public String showOrganizationForm(Model model) {
        model.addAttribute("organization", new Organization());
        return "organization/organization_form";
    }

    @PostMapping(value = "/u/organizations/new")
    public String addOrganization(@Validated @ModelAttribute Organization organization, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "organization/organization_form";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        organizationService.addByUser(organization, user);
        return "redirect:/u/organizations";
    }

    @GetMapping(value = "/u/organizations")
    public String showOrganizations(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("ownedOrganizations", organizationService.findByCreator(user));
        model.addAttribute("involvedOrganizations", organizationService.findByMember(user));
        return "organization/organizations";
    }

    @GetMapping(value = "/u/{organizationName}/projects")
    public String showProjects(@PathVariable("organizationName") String organizationName, Model model) {
        Organization organization = organizationService.findByName(organizationName);
        List<Project> projects = organizationService.getProjects(organization);
        List<ProjectDto> projectDtos = projects.stream().map(project -> projectService.convertToDto(project)).collect(Collectors.toList());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projects", projectDtos);
        model.addAttribute("module", "projects");
        return "project/projects";
    }

}
