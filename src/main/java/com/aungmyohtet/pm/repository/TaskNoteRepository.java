package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;

public interface TaskNoteRepository {

    void save(TaskNote taskNote);

    TaskNote findById(int id);

    List<TaskNote> findAll();

    List<TaskNote> findByCommentedBy(User user);

    List<TaskNote> findByTask(Task task);
}
