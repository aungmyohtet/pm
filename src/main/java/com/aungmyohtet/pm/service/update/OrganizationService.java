package com.aungmyohtet.pm.service.update;

import java.util.Date;
import java.util.List;

import com.aungmyohtet.pm.dto.OrganizationDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;

public interface OrganizationService {

    List<Organization> findByCreator(User user);

    List<Organization> findByMember(User user);

    List<Project> getProjects(Organization organization);

    List<Board> getBoards(Organization organization);

    List<Event> getEvents(Organization organization);

    List<Resource> getResources(Organization organization);

    void addProject(Organization organization, Project project);

    void addBoard(Organization organization, Board board);

    void addEvent(Organization organization, Event event);

    void addResource(Organization organizaiton, Resource resource);

    void addMember(Organization organization, User user);

    void removeBoard(Organization organization, Board board);

    void removeProject(Organization organization, Project project);

    void removeResource(Organization organization, Resource resource);

    void removeMember(Organization organization, User user);

    void transferProject(Project project, Organization from, Organization to);

    void transferBoard(Board board, Organization from, Organization to);

    void transferResource(Resource resource, Organization from, Organization to);

    void transferMember(User user, Organization from, Organization to);


    // searching methods
    List<Project> getProjectsFilteredByName(Organization organization, String projectName);

    List<Board> getBoardsFilteredByName(Organization organization, String boardName);

    List<Event> getEventsFilteredByTitle(Organization organization, String eventTitle);

    List<Event> getEventsFilteredByDates(Organization organization, Date start, Date end);

    List<Event> getEventsFilteredByStartDate(Organization organization, Date start);

    List<Event> getEventsFilteredByEndDate(Organization organization, Date end);
    
    // wrapper for repository methods
    List<Organization> findAll();
    
    void save(Organization organization);
    
    void delete(Organization organization);
    
    Organization findByName(String organizationName);
    
    OrganizationDto convertToDto(Organization organization);


}
