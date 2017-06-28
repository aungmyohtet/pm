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

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
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

}
