package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;

public interface TaskService2 {

    void addAssigneeToTask(User user, Task task);

    void addTaskNoteToTask(TaskNote taskNote, Task task);

    void addStatusToTask(Status status, Task task);

    void addTechnologyTagToTask(TechnologyTag technologyTag, Task task);

    void removeAssigneeToTask(User user, Task task);

    void removeTaskNoteFromTask(TaskNote taskNote, Task task);

    void removeStatusFromTask(Status status, Task task);

    void removeTechnologyTagFromTask(TechnologyTag technologyTag, Task task);

}
