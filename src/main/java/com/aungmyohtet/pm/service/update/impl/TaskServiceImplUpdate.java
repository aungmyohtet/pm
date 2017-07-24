package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.dto.TaskDto;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.TaskService;

@Service
public class TaskServiceImplUpdate implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void assign(Task task, User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unassign(Task task, User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<TaskNote> getTaskNotes(Task task) {
        // TODO Auto-generated method stub
        return null;
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
    public Task findByNoAndProject(int no, Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskDto convertToDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

}
