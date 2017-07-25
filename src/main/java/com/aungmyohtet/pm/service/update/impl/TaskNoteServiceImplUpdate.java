package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.dto.TaskNoteDto;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.repository.update.TaskNoteRepository;
import com.aungmyohtet.pm.service.update.TaskNoteService;

@Service
public class TaskNoteServiceImplUpdate implements TaskNoteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskNoteRepository taskNoteRepository;

    @Override
    public void save(TaskNote taskNote) {
        this.taskNoteRepository.save(taskNote);
    }

    @Override
    public List<TaskNote> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(TaskNote taskNote) {
        // TODO Auto-generated method stub

    }

    @Override
    public TaskNote findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaskNote> findByTask(Task task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskNoteDto convertToDto(TaskNote taskNote) {
        return modelMapper.map(taskNote, TaskNoteDto.class);
    }

}
