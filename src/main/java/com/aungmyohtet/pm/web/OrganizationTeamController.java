package com.aungmyohtet.pm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationTeam;
import com.aungmyohtet.pm.service.OrganizationService;
import com.aungmyohtet.pm.service.OrganizationTeamService;

@Controller
public class OrganizationTeamController {

    @Autowired
    private OrganizationTeamService organizationTeamService;

    @Autowired
    private OrganizationService organizationService;
    
    @ModelAttribute("module")
    String module() {
        return "teams";
    }

    @RequestMapping(value = "/{organizationName}/teams/new", method = RequestMethod.GET)
    public String showOrganizationTeamForm(Model model, @PathVariable("organizationName") String organizationName) {
        model.addAttribute("organizationTeam", new OrganizationTeam());
        model.addAttribute("organizationName", organizationName);
        return "organizationTeamForm";
    }

    @RequestMapping(value = "{organizationName}/teams/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute OrganizationTeam organizationTeam, BindingResult result, Model model,
            @PathVariable("organizationName") String organizationName) {
        if (result.hasErrors()) {
            return "organizationTeamForm";
        }
        Organization organization = organizationService.findByName(organizationName);
        organizationTeam.setOrganization(organization);
        organizationTeamService.save(organizationTeam);
        return "redirect:/{organizationName}/teams";
    }
}
