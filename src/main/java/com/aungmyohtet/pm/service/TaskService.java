package com.aungmyohtet.pm.service;

import java.util.List;

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

    void findProjectAndAddTask(String organizationName, String projectName, Task task);

    void findTaskAndAssignUser(String organizationName, String projectName, int taskNo, String email);

    void findTaskAndAddCommentByUser(String organizationName, String projectName, int taskNo, TaskNote taskNote, String email);

    List<Task> findByOrganizationNameAndProjectName(String organizationName, String projectName);

    List<TaskNote> findTaskNotes(String organizationName, String projectName, int taskNo);

    Task find(String organizationName, String projectName, int taskNo);

}
