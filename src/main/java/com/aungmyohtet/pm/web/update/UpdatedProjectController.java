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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aungmyohtet.pm.dto.TaskDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.OrganizationService;
import com.aungmyohtet.pm.service.update.ProjectService;
import com.aungmyohtet.pm.service.update.TaskService;
import com.aungmyohtet.pm.service.update.UserService;

@Controller
public class UpdatedProjectController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/u/{organizationName}/projects/new")
    public String showForm(Model model, @PathVariable("organizationName") String organizationName) {
        model.addAttribute("project", new Project());
        model.addAttribute("organizationName", organizationName);
        return "project/project_form";
    }

    @PostMapping(value = "/u/{organizationName}/projects/new")
    public String addProject(@Validated @ModelAttribute Project project, BindingResult result, Model model, @PathVariable("organizationName") String organizationName) {
        if (result.hasErrors()) {
            return "project/project_form";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Organization organization = organizationService.findByName(organizationName);
        User user = userService.findByEmail(email);
        organizationService.addProjectByUser(organization, project, user);
        return "redirect:/u/" + organizationName + "/projects";
    }

    @GetMapping(value = "/u/{organizationName}/projects/{projectName}/tasks")
    public String showProjectTasks(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        model.addAttribute("tasks", projectService.getTasks(project));
        return "task/tasks";
    }

   @RequestMapping(value = "/u/{organizationName}/projects/{projectName}/gantt", method = RequestMethod.GET)
    public String showGanttChart(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        List<Task> tasks = projectService.getTasks(project);
        List<TaskDto> taskDtos = tasks.stream().map(task -> taskService.convertToDto(task)).collect(Collectors.toList());
        model.addAttribute("tasks", taskDtos);
        return "showGanttChart";
    }

}
