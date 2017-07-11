package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.StatusRepository;
import com.aungmyohtet.pm.repository.TaskNoteRepository;
import com.aungmyohtet.pm.repository.TaskRepository;
import com.aungmyohtet.pm.repository.TechnologyTagRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskNoteRepository taskNoteRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TechnologyTagRepository technologyTagRepository;

    @Override
    @Transactional
    public void addToProject(Task task, int projectId) {
        Project project = projectRepository.findById(projectId);
        // * This works.
        // task.setProject(project);
        // taskRepository.save(task);
        // * //

        // * This also works? yes this also works
        project.getTasks().add(task);
        task.setProject(project);// id of project missing without this line
        projectRepository.save(project);
        // *//
    }

    @Override
    @Transactional
    public void assignUserToTask(String userEmail, int taskId) {
        User user = userRepository.findByEmail(userEmail);
        Task task = taskRepository.findById(taskId);
        task.getAssignees().add(user);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void addCommentToTaskByUser(String comment, int taskId, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        Task task = taskRepository.findById(taskId);
        TaskNote taskNote = new TaskNote();
        taskNote.setCommentedBy(user);
        taskNote.setTask(task);
        taskNote.setComment(comment);
        taskNoteRepository.save(taskNote);
    }

    @Override
    @Transactional
    public void findProjectAndAddTask(int organizationId, String projectName, Task task) {
        Project project = projectRepository.findByOrganizationIdAndProjectName(organizationId, projectName);
        task.setProject(project);
        int currentMaxTaskNo = taskRepository.findTaskMaxNoByOrganizationAndProject(organizationId, projectName);
        task.setNo(currentMaxTaskNo + 1);
        project.getTasks().add(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName) {
        return taskRepository.findTaskMaxNoByOrganizationAndProject(organizationId, projectName);
    }

    @Override
    @Transactional
    public void findTaskAndAssignUser(int organizationId, String projectName, int taskNo, String userEmail) {
        Task task = taskRepository.find(organizationId, projectName, taskNo);
        User user = userRepository.findByEmail(userEmail);
        task.getAssignees().add(user);
    }

    @Override
    @Transactional
    public void findTaskAndAddCommentByUser(int organizationId, String projectName, int taskNo, TaskNote taskNote, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        Task task = taskRepository.find(organizationId, projectName, taskNo);
        taskNote.setTask(task);
        taskNote.setCommentedBy(user);
        taskNoteRepository.save(taskNote);
    }

    @Override
    @Transactional
    public void findProjectAndAddTask(String organizationName, String projectName, Task task) {
        Project project = projectRepository.findByOrganizationNameAndProjectName(organizationName, projectName);
        task.setProject(project);
        List<Task> tasks = taskRepository.find(organizationName, projectName);
        if (tasks == null || tasks.size() == 0) {
            task.setNo(1);
        } else {
            int currentMaxTaskNo = taskRepository.findTaskMaxNoByOrganizationNameAndProjectName(organizationName, projectName);
            task.setNo(currentMaxTaskNo + 1);
        }
        // project.getTasks().add(task);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void findTaskAndAssignUser(String organizationName, String projectName, int taskNo, String email) {
        Task task = taskRepository.find(organizationName, projectName, taskNo);
        User user = userRepository.findByEmail(email);
        task.getAssignees().add(user);
    }

    @Override
    @Transactional
    public void findTaskAndAddCommentByUser(String organizationName, String projectName, int taskNo, TaskNote taskNote, String email) {
        User user = userRepository.findByEmail(email);
        Task task = taskRepository.find(organizationName, projectName, taskNo);
        taskNote.setTask(task);
        taskNote.setCommentedBy(user);
        taskNoteRepository.save(taskNote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByOrganizationNameAndProjectName(String organizationName, String projectName) {
        return taskRepository.find(organizationName, projectName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskNote> findTaskNotes(String organizationName, String projectName, int taskNo) {
        return taskRepository.findTaskNotes(organizationName, projectName, taskNo);
    }

    @Override
    @Transactional(readOnly = true)
    public Task find(String organizationName, String projectName, int taskNo) {
        return taskRepository.find(organizationName, projectName, taskNo);
    }

    @Override
    @Transactional
    public void findTaskAndAddStatus(int organizationId, String projectName, int taskNo, int statusId) {
        Task task = taskRepository.find(organizationId, projectName, taskNo);
        Status statusResult = statusRepository.findById(statusId);
        task.getStatus().add(statusResult);
    }

    @Override
    @Transactional
    public void findTaskAndAddStatus(String organizationName, String projectName, int taskNo, int statusId) {
        Task task = taskRepository.find(organizationName, projectName, taskNo);
        Status statusResult = statusRepository.findById(statusId);
        task.getStatus().add(statusResult);
    }

    @Override
    @Transactional
    public void findTaskAndAddTechnologyTag(int organizationId, String projectName, int taskNo, int technologyTagId) {
        Task task = taskRepository.find(organizationId, projectName, taskNo);
        Status statusResult = statusRepository.findById(technologyTagId);
        task.getStatus().add(statusResult);
    }

    @Override
    @Transactional
    public void findTaskAndAddTechnologyTag(String organizationName, String projectName, int taskNo, int technologyTagId) {
        Task task = taskRepository.find(organizationName, projectName, taskNo);
        TechnologyTag technologyTagResult = technologyTagRepository.findById(technologyTagId);
        task.getTechnologyTag().add(technologyTagResult);
    }
}