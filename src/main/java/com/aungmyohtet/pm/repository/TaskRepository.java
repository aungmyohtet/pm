package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Task;

public interface TaskRepository {

    void save(Task task);

    Task findById(int id);

    Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName);

    Task find(int organizationId, String projectName, int taskNo);

    Integer findTaskMaxNoByOrganizationNameAndProjectName(String organizationName, String projectName);

    Task find(String organizationName, String projectName, int taskNo);

    List<Task> find(String organizationName, String projectName);

}
