package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.dto.TaskNoteDto;
import com.aungmyohtet.pm.entity.TaskNote;

public interface TaskNoteService {
    
    TaskNoteDto convertToDto(TaskNote taskNote);

}
