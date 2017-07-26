package com.aungmyohtet.pm.web.update;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aungmyohtet.pm.dto.TaskNoteDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.OrganizationService;
import com.aungmyohtet.pm.service.update.ProjectService;
import com.aungmyohtet.pm.service.update.TaskNoteService;
import com.aungmyohtet.pm.service.update.TaskService;
import com.aungmyohtet.pm.service.update.UserService;
import com.aungmyohtet.pm.validator.DateEntryValidator;

@Controller
public class UpdatedTaskController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskNoteService taskNoteService;

    @Autowired
    @Qualifier("dateValidator")
    private DateEntryValidator dateValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/new")
    public String showTaskForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("task", new Task());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        return "task/form";
    }

    @PostMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/new")
    public String addProject(@Validated @ModelAttribute Task task, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName) {
        if (result.hasErrors()) {
            return "task/form";
        }
        dateValidator.validate(task, result);
        if (result.hasErrors()) {
            return "task/form";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        projectService.addTask(project, task);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks";
    }

    @GetMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/{taskNo}/assign")
    public String showAssigneeForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "task/assignee_form";
    }

    @PostMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/{taskNo}/assign")
    public String assign(@ModelAttribute User userForm, Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        Task task = taskService.findByNoAndProject(taskNo, project);
        User user = userService.findByEmail(userForm.getEmail());
        taskService.assign(task, user);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @GetMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments")
    @ResponseBody
    public List<TaskNoteDto> getComments(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        Task task = taskService.findByNoAndProject(taskNo, project);
        List<TaskNote> taskNotes = taskService.getTaskNotes(task);
        List<TaskNoteDto> taskNoteDtos = taskNotes.stream().map(taskNote -> taskNoteService.convertToDto(taskNote)).collect(Collectors.toList());
        return taskNoteDtos;
    }

    @PostMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments/new")
    @ResponseBody
    public TaskNoteDto addComment(@RequestBody TaskNoteDto taskNoteDto, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        Task task = taskService.findByNoAndProject(taskNo, project);
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        TaskNote taskNote = new TaskNote();
        taskNote.setComment(taskNoteDto.getComment());
        taskNote.setCommentedBy(user);
        taskNote.setTask(task);
        taskNoteService.save(taskNote);
        return taskNoteDto;
    }

    @GetMapping(value = "/u/{organizationName}/projects/{projectName}/tasks/{taskNo}")
    public String showDetails(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        Organization organization = organizationService.findByName(organizationName);
        Project project = projectService.findByNameAndOrganization(projectName, organization);
        Task task = taskService.findByNoAndProject(taskNo, project);
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        model.addAttribute("taskTitle", task.getTitle());
        return "task/details";
    }
}
