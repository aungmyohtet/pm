package com.aungmyohtet.pm.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.dto.TaskNoteDto;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.service.TaskNoteService;

@Service
public class TaskNoteServiceImpl implements TaskNoteService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskNoteDto convertToDto(TaskNote taskNote) {
        return modelMapper.map(taskNote, TaskNoteDto.class);
    }

}
