package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;

public interface TaskNoteService {

    void save(TaskNote taskNote);

    List<TaskNote> findAll();

    void delete(TaskNote taskNote);

    TaskNote findById(int id);

    List<TaskNote> findByTask(Task task);
}
