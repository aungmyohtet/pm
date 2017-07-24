package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;

public interface TaskNoteRepository {

    void save(TaskNote taskNote);

    List<TaskNote> findAll();

    void delete(TaskNote taskNote);

    TaskNote findById(int id);

    List<TaskNote> findByTask(Task task);
}
