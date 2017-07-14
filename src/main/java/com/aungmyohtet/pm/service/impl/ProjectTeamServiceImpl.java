package com.aungmyohtet.pm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectTeam;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.ProjectTeamRepository;
import com.aungmyohtet.pm.service.ProjectTeamService;

@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Autowired
    private ProjectTeamRepository projectTeamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public List<ProjectTeam> findByProjectNameAndOrganizationName(String organizationName, String projectName) {
        return projectTeamRepository.findByProjectNameAndOrganizationName(organizationName, projectName);
    }

    @Override
    @Transactional
    public void findProjectAndAddTeam(String organizationName, String projectName, ProjectTeam projectTeam) {
        Project project = projectRepository.findByOrganizationNameAndProjectName(organizationName, projectName);
        projectTeam.setProject(project);
        projectTeamRepository.save(projectTeam);

    }

}
