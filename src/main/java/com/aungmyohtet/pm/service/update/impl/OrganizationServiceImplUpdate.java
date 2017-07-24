package com.aungmyohtet.pm.service.update.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.OrganizationDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.BoardRepository;
import com.aungmyohtet.pm.repository.update.EventRepository;
import com.aungmyohtet.pm.repository.update.OrganizationRepository;
import com.aungmyohtet.pm.repository.update.ProjectRepository;
import com.aungmyohtet.pm.repository.update.ResourceRepository;
import com.aungmyohtet.pm.repository.update.RoleRepository;
import com.aungmyohtet.pm.service.update.OrganizationService;

@Service
public class OrganizationServiceImplUpdate implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findByCreator(User user) {
        return this.organizationRepository.findByCreator(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findByMember(User user) {
        return this.organizationRepository.findByMember(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getProjects(Organization organization) {
        return this.projectRepository.findByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getBoards(Organization organization) {
        return this.boardRepository.findByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(Organization organization) {
        return eventRepository.findByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resource> getResources(Organization organization) {
        return this.resourceRepository.findByOrganization(organization);
    }

    @Override
    @Transactional
    public void addProject(Organization organization, Project project) {
        project.setOrganization(organization);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void addBoard(Organization organization, Board board) {
        board.setOrganization(organization);
        boardRepository.save(board);
    }

    @Override
    @Transactional
    public void addEvent(Organization organization, Event event) {
        event.setOrganization(organization);
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void addResource(Organization organization, Resource resource) {
        resource.setOrganization(organization);
        resourceRepository.save(resource);
    }

    @Override
    @Transactional
    public void addMember(Organization organization, User user) {
        OrganizationMember member = new OrganizationMember();
        member.setOrganization(organization);
        member.setUser(user);
        member.setRole(roleRepository.getDefaultRole());
        organization.getOrganizationMembers().add(member);
        organizationRepository.save(organization);
    }

    @Override
    public void removeBoard(Organization organization, Board board) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProject(Organization organization, Project project) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeResource(Organization organization, Resource resource) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeMember(Organization organization, User user) {
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
    public List<Project> getProjectsFilteredByName(Organization organization, String projectName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Board> getBoardsFilteredByName(Organization organization, String boardName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> getEventsFilteredByTitle(Organization organization, String eventTitle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> getEventsFilteredByDates(Organization organization, Date start, Date end) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> getEventsFilteredByStartDate(Organization organization, Date start) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> getEventsFilteredByEndDate(Organization organization, Date end) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        return this.organizationRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Organization organization) {
        this.organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public void delete(Organization organization) {
        this.organizationRepository.delete(organization);
    }

    @Override
    @Transactional
    public Organization findByName(String organizationName) {
        return this.organizationRepository.findByName(organizationName);
    }

    @Override
    public OrganizationDto convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    @Transactional
    public void addByUser(Organization organization, User user) {
        OrganizationMember member = new OrganizationMember();
        member.setOrganization(organization);
        member.setUser(user);
        member.setRole(roleRepository.findByName("MANAGER"));
        organization.getOrganizationMembers().add(member);
        organizationRepository.save(organization);
    }

}
