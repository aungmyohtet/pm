package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Task;

public interface TaskService {

    void addToProject(Task task, int projectId);

    void assignUserToTask(String userEmail, int taskId);

    void addCommentToTaskByUser(String comment, int taskId, String userEmail);

    void findProjectAndAddTask(int organizationId, String projectName, Task task);

    Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName);

}
