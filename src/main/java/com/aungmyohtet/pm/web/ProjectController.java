package com.aungmyohtet.pm.web;

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

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.service.ProjectService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/organizations/{organizationId}/projects/new", method = RequestMethod.GET)
    public String showProjectForm(Model model, @PathVariable("organizationId") int organizationId) {
        model.addAttribute("project", new Project());
        model.addAttribute("organizationId", organizationId);
        return "projectForm";
    }

    @RequestMapping(value = "/organizations/{organizationId}/projects/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute Project project, BindingResult result, Model model, @PathVariable("organizationId") int organizationId) {
        if (result.hasErrors()) {
            return "projectForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        projectService.addToOrganizationByUser(project, organizationId, email);
        return "redirect:/organizations";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @ResponseBody
    public String list(Model model) {
        return "To show project list";
    }

}
