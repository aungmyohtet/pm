package com.aungmyohtet.pm.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.StatusService;
import com.aungmyohtet.pm.service.TaskService;
import com.aungmyohtet.pm.service.TechnologyTagService;
import com.aungmyohtet.pm.service.UserService;
import com.aungmyohtet.pm.validator.DateEntryValidator;

@Controller
public class TaskController2 {

    @Autowired
    private TaskService taskService;

    @ModelAttribute("module")
    String module() {
        return "projects";
    }

    @Autowired
    private StatusService statusService;

    @Autowired
    private TechnologyTagService technologyTagService;

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

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks", method = RequestMethod.GET)
    public String showProjectTasks(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("tasks", taskService.findByOrganizationNameAndProjectName(organizationName, projectName));
        return "tasks";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/gantt", method = RequestMethod.GET)
    public String showGanttChart(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        List<Task> tasks = taskService.findByOrganizationNameAndProjectName(organizationName, projectName);
        model.addAttribute("tasks", tasks.stream().map(task -> taskService.convertToDto(task)).collect(Collectors.toList()));
        return "showGanttChart";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/new", method = RequestMethod.GET)
    public String showTaskForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName) {
        model.addAttribute("task", new Task());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        return "taskForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/new", method = RequestMethod.POST)
    public String addProject(@Validated @ModelAttribute Task task, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName) {
        if (result.hasErrors()) {
            return "taskForm";
        }
        dateValidator.validate(task, result);
        if (result.hasErrors()) {
            return "taskForm";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // we used email in user details service
        taskService.findProjectAndAddTask(organizationName, projectName, task);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/assign", method = RequestMethod.GET)
    public String showAssigneeForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
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
    public String assign(@ModelAttribute User user, Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        taskService.findTaskAndAssignUser(organizationName, projectName, taskNo, user.getEmail());
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments/new", method = RequestMethod.GET)
    public String showTaskNoteForm(Model model, @PathVariable("organizationId") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "taskNoteForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/comments/new", method = RequestMethod.POST)
    private String createTaskNote(@ModelAttribute TaskNote taskNote, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        taskService.findTaskAndAddCommentByUser(organizationName, projectName, taskNo, taskNote, email);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/status/new", method = RequestMethod.GET)
    public String showTaskStatusForm(Model model, @PathVariable("organizationId") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("status", new Status());
        model.addAttribute("statusNames", statusService.findAll());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "taskStatusForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/status/new", method = RequestMethod.POST)
    private String createTaskStatus(@ModelAttribute Status status, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        String statusName = request.getParameter("name");
        int statusId = Integer.parseInt(statusName);

        taskService.findTaskAndAddStatus(organizationName, projectName, taskNo, statusId);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/technologyTag/new", method = RequestMethod.GET)
    public String showTechnologyTagForm(Model model, @PathVariable("organizationId") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        // model.addAttribute("status", new Status());
        // model.addAttribute("statusNames", statusService.findAll());
        model.addAttribute("technologyTag", new TechnologyTag());
        model.addAttribute("technologyNames", technologyTagService.findAll());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        return "taskTechnologyTagForm";
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}/technologyTag/new", method = RequestMethod.POST)
    private String createTaskTechnologyTag(@ModelAttribute Status status, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("projectName") String projectName, @PathVariable("taskNo") int taskNo, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        String technologyTagName = request.getParameter("name");
        int technologyId = Integer.parseInt(technologyTagName);

        taskService.findTaskAndAddTechnologyTag(organizationName, projectName, taskNo, technologyId);
        return "redirect:/" + organizationName + "/projects/" + projectName + "/tasks/" + taskNo;
    }

    @RequestMapping(value = "/{organizationName}/projects/{projectName}/tasks/{taskNo}", method = RequestMethod.GET)
    public String showTaskDetails(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("projectName") String projectName,
            @PathVariable("taskNo") int taskNo) {
        model.addAttribute("taskNote", new TaskNote());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("projectName", projectName);
        model.addAttribute("taskNo", taskNo);
        model.addAttribute("comments", taskService.findTaskNotes(organizationName, projectName, taskNo));
        model.addAttribute("user", new User());
        model.addAttribute("status", new Status());
        model.addAttribute("technologyTag", new TechnologyTag());
        model.addAttribute("task", taskService.find(organizationName, projectName, taskNo));
        model.addAttribute("statusNamesResult", statusService.find(taskService.find(organizationName, projectName, taskNo)));
        model.addAttribute("statusNames", statusService.findAll());
        model.addAttribute("technologyTagNamesResult", technologyTagService.find(taskService.find(organizationName, projectName, taskNo)));
        model.addAttribute("technologyTagNames", technologyTagService.findAll());
        return "taskDetails";
    }

}
