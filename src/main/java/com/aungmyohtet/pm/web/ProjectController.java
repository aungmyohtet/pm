package com.aungmyohtet.pm.web;

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

import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.ProjectService;
import com.aungmyohtet.pm.service.UserService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{organizationName}/projects/new", method = RequestMethod.GET)
    public String showProjectForm(Model model, @PathVariable("organizationName") String organizationName) {
        model.addAttribute("project", new Project());
        model.addAttribute("organizationName", organizationName);
        return "projectForm";
    }

    @RequestMapping(value = "{organizationName}/projects/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute Project project, BindingResult result, Model model, @PathVariable("organizationName") String organizationName) {
        if (result.hasErrors()) {
            return "projectForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        projectService.addToOrganizationByUser(project, organizationName, email);
        return "redirect:/organizations";
    }

    @RequestMapping(value = "{organizationName}/projects/{projectName}/members", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> showProjectMembers(@PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName, Model model) {
        List<User> users = userService.findMembersOfProject(organizationName, projectName);
        return users.stream().map(user -> userService.converToDto(user)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/members/new", method = RequestMethod.GET)
    private String showProjectMemberForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        return "projectMemberForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/members/new", method = RequestMethod.POST)
    @ResponseBody
    private String addMemberToProject(@ModelAttribute User user, Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        projectService.addMemberToProject(user.getEmail(), organizationName, projectName);
        return "successfully added member to project";
    }

}
