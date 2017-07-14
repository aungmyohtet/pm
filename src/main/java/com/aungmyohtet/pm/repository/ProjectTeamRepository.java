package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.ProjectTeam;

public interface ProjectTeamRepository {

    List<ProjectTeam> findByProjectNameAndOrganizationName(String organizationName, String projectName);

    void save(ProjectTeam projectTeam);

}
