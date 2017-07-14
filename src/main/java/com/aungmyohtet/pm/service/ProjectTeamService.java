package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.ProjectTeam;

public interface ProjectTeamService {

    List<ProjectTeam> findByProjectNameAndOrganizationName(String organizationName, String projectName);

    void findProjectAndAddTeam(String organizationName, String projectName, ProjectTeam projectTeam);

}
