package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.TaskNoteRepository;
import com.aungmyohtet.pm.repository.TaskRepository;
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

    @Override
    @Transactional
    public void addToProject(Task task, int projectId) {
        Project project = projectRepository.findById(projectId);
        //* This works.
        //task.setProject(project);
        //taskRepository.save(task);
        //* //
        
        //* This also works? yes this also works
        project.getTasks().add(task);
        task.setProject(project);// id of project missing without this line
        projectRepository.save(project);
        //*//
    }

    @Override
    @Transactional
    public void assignUserToTask(String userEmail, int taskId) {
        User user = userRepository.findByEmail(userEmail);
        Task task = taskRepository.findById(taskId);
        task.setAssignee(user);
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

}
