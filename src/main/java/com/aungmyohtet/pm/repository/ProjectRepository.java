package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Project;

public interface ProjectRepository {

    void save(Project project);

    Project findById(int id);

    List<Project> findByOrganization(int organizationId);

    Project findByOrganizationIdAndProjectName(int organizationId, String projectName);
}
