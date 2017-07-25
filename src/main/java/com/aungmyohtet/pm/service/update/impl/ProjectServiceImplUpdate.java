package com.aungmyohtet.pm.service.update.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectMember;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.ProjectRepository;
import com.aungmyohtet.pm.repository.update.RoleRepository;
import com.aungmyohtet.pm.repository.update.TaskRepository;
import com.aungmyohtet.pm.repository.update.UserRepository;
import com.aungmyohtet.pm.service.update.ProjectService;

@Service
public class ProjectServiceImplUpdate implements ProjectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getMembers(Project project) {
        return userRepository.findByProject(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasks(Project project) {
        return taskRepository.findByProject(project);
    }

    @Override
    public List<ProjectMember> getProjectMembers(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getMembersByRole(Project project, Role role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> getTasksBetweenDate(Project project, Date start, Date end) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> getTasksByAssignee(Project project, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTask(Project project, Task task) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Project> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> findByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> findByOrganizationName(String organizationName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Project project) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Project> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Project findByNameAndOrganization(String name, Organization organization) {
        return projectRepository.findByNameAndOrganization(name, organization);
    }

    @Override
    public List<Project> findByNameAndOrganizationName(String name, String organizationName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Project findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectDto convertToDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    @Transactional
    public void addMember(Project project, User user) {
        // Why this is working?
        // It can be made better?
        Project projectEntity = projectRepository.findById(project.getId());
        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(roleRepository.findByName("DEVELOPER"));
        projectEntity.getProjectMembers().add(member);
    }

}
