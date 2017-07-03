package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.entity.Project;

public interface ProjectService {

    void save(Project project);

    void addToOrganizationByUser(Project project, int organizationId, String userEmail);

    ProjectDto converToDto(Project project);

    List<Project> findByOrganization(int organizationId);

    void addMemberToProject(String userEmail, int organizationId, String projectName);

    void addToOrganizationByUser(Project project, String organizationName, String email);

    void addMemberToProject(String email, String organizationName, String projectName);
}
