package com.aungmyohtet.pm.web.update;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

import com.aungmyohtet.pm.dto.EventDto;
import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.EventService;
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

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/u/organizations/new")
    public String showForm(Model model) {
        model.addAttribute("organization", new Organization());
        return "organization/organization_form";
    }

    @PostMapping(value = "/u/organizations/new")
    public String add(@Validated @ModelAttribute Organization organization, BindingResult result, Model model) {
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

    @GetMapping(value = "/u/{organizationName}")
    public String enterOrganization(@PathVariable("organizationName") String orgName) {
        return "redirect:/u/" + orgName + "/projects";
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

    @GetMapping(value = "/u/{organizationName}/boards")
    public String showBoards(@PathVariable("organizationName") String organizationName, Model model) {
        Organization organization = organizationService.findByName(organizationName);
        List<Board> boards = organizationService.getBoards(organization);
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boards", boards);
        model.addAttribute("module", "boards");
        return "board/boards";
    }

    @GetMapping(value = "/u/{organizationName}/resources")
    public String showResources(@PathVariable("organizationName") String organizationName, Model model) {
        Organization organization = organizationService.findByName(organizationName);
        List<Resource> resources = organizationService.getResources(organization);
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("resources", resources);
        model.addAttribute("module", "resources");
        return "resource/resources";
    }

    @GetMapping(value = "/u/{organizationName}/events")
    public String showEvents(@PathVariable("organizationName") String organizationName, Model model, HttpServletRequest request) {
        Organization organization = organizationService.findByName(organizationName);
        List<Event> events = organizationService.getEvents(organization);
        List<EventDto> eventDtos = events.stream().map(event -> eventService.convertToDto(event)).collect(Collectors.toList());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("module", "events");
        model.addAttribute("events", eventDtos);
        return "event/events";
    }

    @GetMapping(value = "/u/{organizationName}/members")
    public String showOrganizationMembers(@PathVariable("organizationName") String organizationName, Model model) {
        Organization organization = organizationService.findByName(organizationName);
        List<User> users = organizationService.getMembers(organization);
        List<UserDto> userDtos = users.stream().map(user -> userService.convertToDto(user)).collect(Collectors.toList());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("members", userDtos);
        model.addAttribute("module", "members");
        return "organization/members";
    }

    @GetMapping(value = "/u/{organizationName}/members/new")
    public String showMemberForm(@PathVariable("organizationName") String organizationName, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationName", organizationName);
        return "organization/member_form";
    }

    @PostMapping(value = "/u/{organizationName}/members/new")
    public String addMember(@PathVariable("organizationName") String organizationName, Model model, @ModelAttribute User userForm) {
        Organization organization = organizationService.findByName(organizationName);
        User user = userService.findByEmail(userForm.getEmail());
        organizationService.addMember(organization, user);
        return "redirect:/" + organizationName + "/members";
    }


}
