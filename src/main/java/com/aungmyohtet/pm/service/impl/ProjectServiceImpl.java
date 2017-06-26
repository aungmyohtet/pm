package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectMember;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(Project project) {
	projectRepository.save(project);
    }

    @Override
    @Transactional
    public void addToOrganizationByUser(Project project, int organizationId, String userEmail) {
	User user = userRepository.findByEmail(userEmail);
	ProjectMember projectMember = new ProjectMember();
	projectMember.setProject(project);
	projectMember.setUser(user);
	projectMember.setRole("owner");
	project.getProjectMembers().add(projectMember);
	Organization organization = organizationRepository.findById(organizationId);
	project.setOrganization(organization);
	projectRepository.save(project);
    }

}
