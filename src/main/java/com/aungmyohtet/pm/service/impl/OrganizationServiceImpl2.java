package com.aungmyohtet.pm.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectMember;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.RoleRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.OrganizationService2;

@Service
public class OrganizationServiceImpl2 implements OrganizationService2 {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Set<Organization> findOrganizationsCreatedByUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Organization> findOrganizationsInvolvingUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Project> findProjectsByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Board> findBoardsByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Event> findEventsByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Resource> findResourcesByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(#organization, 'create-project')")
    public void addProjectToOrganization(Project project, Organization organization) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail);
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setRole(roleRepository.findByName("OWNER"));
        project.getProjectMembers().add(projectMember);
        project.setOrganization(organization);
        projectRepository.save(project);
    }

    @Override
    public void addBoardToOrganization(Board board, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addEventToOrganization(Event event, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addResourceToOrganization(Resource resource, Organization organizaiton) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addMemberToOrganization(User user, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeBoardFromOrganization(Board board, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProjectFromOrganization(Project project, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeResourceFromOrganization(Resource resource, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeUserFromOrganization(User user, Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferProject(Project project, Organization from, Organization to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferBoard(Board board, Organization from, Organization to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferResource(Resource resource, Organization from, Organization to) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferMember(User user, Organization from, Organization to) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(readOnly = true)
    public Organization findByName(String organizationName) {
        return organizationRepository.findByName(organizationName);
    }

}
