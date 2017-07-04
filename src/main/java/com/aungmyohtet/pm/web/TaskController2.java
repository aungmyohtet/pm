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

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.TaskService;
import com.aungmyohtet.pm.service.UserService;

@Controller
public class TaskController2 {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks", method = RequestMethod.GET)
    public String showProjectTasks(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("tasks", taskService.findByOrganizationNameAndProjectName(organizationName, projectName));
        return "tasks";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/new", method = RequestMethod.GET)
    public String showTaskForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("task", new Task());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        return "taskForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute Task task, BindingResult result, Model model,@PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        if (result.hasErrors()) {
            return "taskForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        taskService.findProjectAndAddTask(organizationName, projectName, task);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/assign", method = RequestMethod.GET)
    public String showAssigneeForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        model.addAttribute("user", new User());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "taskAssigneeForm";
    }

    @RequestMapping(value = "/organizations/{organizationId}/projects/{projectName}/max_task_no", method = RequestMethod.GET)
    @ResponseBody
    public String showMaxTaskNo(Model model, @PathVariable("organizationId") int organizationId, @PathVariable("projectName") String projectName) {
        int maxTaskNoByProject = taskService.findTaskMaxNoByOrganizationAndProject(organizationId, projectName);
        return String.valueOf(maxTaskNoByProject);
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/assign", method = RequestMethod.POST)
    public String assign(@ModelAttribute User user, Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        taskService.findTaskAndAssignUser(organizationName, projectName, taskNo, user.getEmail());
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments/new", method = RequestMethod.GET)
    public String showTaskNoteForm(Model model, @PathVariable("organizationId") String organizationName, @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "taskNoteForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments/new", method = RequestMethod.POST)
    private String createTaskNote(@ModelAttribute TaskNote taskNote, Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        taskService.findTaskAndAddCommentByUser(organizationName, projectName, taskNo, taskNote, email);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}", method = RequestMethod.GET)
    public String showTaskDetails(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        model.addAttribute("comments", taskService.findTaskNotes(organizationName, projectName, taskNo));
        model.addAttribute("user", new User());
        model.addAttribute("task", taskService.find(organizationName, projectName, taskNo));
        return "taskDetails";
    }

}
