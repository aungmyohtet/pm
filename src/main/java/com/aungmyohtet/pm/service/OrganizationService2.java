package com.aungmyohtet.pm.service;

import java.util.Set;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;

public interface OrganizationService2 {

    Set<Organization> findOrganizationsCreatedByUser(User user);

    Set<Organization> findOrganizationsInvolvingUser(User user);

    Set<Project> findProjectsByOrganization(Organization organization);

    Set<Board> findBoardsByOrganization(Organization organization);

    Set<Event> findEventsByOrganization(Organization organization);

    Set<Resource> findResourcesByOrganization(Organization organization);

    void addProjectToOrganization(Project project, Organization organization);

    void addBoardToOrganization(Board board, Organization organization);

    void addEventToOrganization(Event event, Organization organization);

    void addResourceToOrganization(Resource resource, Organization organizaiton);

    void addMemberToOrganization(User user, Organization organization);

    void removeBoardFromOrganization(Board board, Organization organization);

    void removeProjectFromOrganization(Project project, Organization organization);

    void removeResourceFromOrganization(Resource resource, Organization organization);

    void removeUserFromOrganization(User user, Organization organization);

    void transferProject(Project project, Organization from, Organization to);

    void transferBoard(Board board, Organization from, Organization to);

    void transferResource(Resource resource, Organization from, Organization to);

    void transferMember(User user, Organization from, Organization to);

    Organization findByName(String organizationName);

}
