package com.aungmyohtet.pm.repository;

import com.aungmyohtet.pm.entity.Task;

public interface TaskRepository {

    void save(Task task);

    Task findById(int id);

    Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName);
}
