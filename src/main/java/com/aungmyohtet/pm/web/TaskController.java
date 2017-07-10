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

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/projects/{projectId}/tasks/new", method = RequestMethod.GET)
    public String showTaskForm(Model model, @PathVariable("projectId") int projectId) {
        model.addAttribute("task", new Task());
        model.addAttribute("projectId", projectId);
        return "taskForm";
    }

    @RequestMapping(value = "/projects/{projectId}/tasks/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute Task task, BindingResult result, Model model, @PathVariable("projectId") int projectId) {
        if (result.hasErrors()) {
            return "taskForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        taskService.addToProject(task, projectId);
        return "redirect:/organizations";
    }

    @RequestMapping(value = "/tasks/{taskId}/assign", method = RequestMethod.GET)
    public String showAssigneeForm(Model model, @PathVariable("taskId") int taskId) {
        model.addAttribute("user", new User());
        model.addAttribute("taskId", taskId);
        return "taskAssigneeForm";
    }

    @RequestMapping(value = "/tasks/{taskId}/assign", method = RequestMethod.POST)
    public String assign(@ModelAttribute User user, Model model, @PathVariable("taskId") int taskId) {
        taskService.assignUserToTask(user.getEmail(), taskId);
        return "redirect:/organizations";
    }

    @RequestMapping(value = "/tasks/{taskId}/comments/new", method = RequestMethod.GET)
    public String showTaskNoteForm(Model model, @PathVariable("taskId") int taskId) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("taskId", taskId);
        return "taskNoteForm";
    }

    @RequestMapping(value = "/tasks/{taskId}/comments/new", method = RequestMethod.POST)
    private String createTaskNote(@ModelAttribute TaskNote taskNote, Model model, @PathVariable("taskId") int taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        taskService.addCommentToTaskByUser(taskNote.getComment(), taskId, email);
        return "redirect:/organizations";
    }
}
