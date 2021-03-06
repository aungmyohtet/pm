package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.TaskDto;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.TaskNoteRepository;
import com.aungmyohtet.pm.repository.update.TaskRepository;
import com.aungmyohtet.pm.repository.update.UserRepository;
import com.aungmyohtet.pm.service.update.TaskService;

@Service
public class TaskServiceImplUpdate implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskNoteRepository taskNoteRepository;

    @Override
    @Transactional
    public void assign(Task task, User user) {
        // To be managed by entity manager
        task = this.taskRepository.findById(task.getId());
        task.getAssignees().add(user);
    }

    @Override
    public void unassign(Task task, User user) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskNote> getTaskNotes(Task task) {
        return this.taskNoteRepository.findByTask(task);
    }

    @Override
    public void save(Task task) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Task> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Task task) {
        // TODO Auto-generated method stub

    }

    @Override
    public Task findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> findByProject(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Task findByTitleAndProject(String title, Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Task findByNoAndProject(int no, Project project) {
        return this.taskRepository.findByNoAndProject(no, project);
    }

    @Override
    public TaskDto convertToDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    @Transactional
    public void addTech(Task task, TechnologyTag tech) {
        task = this.taskRepository.findById(task.getId());
        task.getTechnologyTag().add(tech);
    }

}
