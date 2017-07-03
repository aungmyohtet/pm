package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;

public interface TaskService {

    void addToProject(Task task, int projectId);

    void assignUserToTask(String userEmail, int taskId);

    void addCommentToTaskByUser(String comment, int taskId, String userEmail);

    void findProjectAndAddTask(int organizationId, String projectName, Task task);

    Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName);

    void findTaskAndAssignUser(int organizationId, String projectName, int taskNo, String userEmail);

    void findTaskAndAddCommentByUser(int organizationId, String projectName, int taskNo, TaskNote taskNote, String userEmail);

}
