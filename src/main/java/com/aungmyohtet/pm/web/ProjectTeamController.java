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

import com.aungmyohtet.pm.entity.ProjectTeam;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.service.ProjectTeamService;

@Controller
public class ProjectTeamController {

    @Autowired
    private ProjectTeamService projectTeamService;

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/teams", method = RequestMethod.GET)
    public String showProjectTeams(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("projectTeams", projectTeamService.findByProjectNameAndOrganizationName(organizationName, projectName));
        return "projectTeams";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/teams/new", method = RequestMethod.GET)
    public String showPrjectTeamForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("projectTeam", new ProjectTeam());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        return "projectTeamForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/teams/new", method = RequestMethod.POST)
    public String addProjectTeam(@Validated @ModelAttribute ProjectTeam projectTeam, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName) {
        if (result.hasErrors()) {
            return "projectTeamForm";
        }
        projectTeamService.findProjectAndAddTeam(organizationName, projectName, projectTeam);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/teams";
    }

}
