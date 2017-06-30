package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Task;

public interface TaskService {

    void addToProject(Task task, int projectId);

    void assignUserToTask(String userEmail, int taskId);

    void addCommentToTaskByUser(String comment, int taskId, String userEmail);

}
