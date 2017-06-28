package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.TaskRepository;
import com.aungmyohtet.pm.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

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

}
