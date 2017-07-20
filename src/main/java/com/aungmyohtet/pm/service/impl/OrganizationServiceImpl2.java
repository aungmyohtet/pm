package com.aungmyohtet.pm.service.impl;

import java.util.Set;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.OrganizationService2;

public class OrganizationServiceImpl2 implements OrganizationService2 {

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
    public void addProjectToOrganization(Project project, Organization organization) {
        // TODO Auto-generated method stub

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

}
