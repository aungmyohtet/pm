package com.aungmyohtet.pm.service.update.impl;

import java.util.Date;
import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectMember;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.update.ProjectService;

public class ProjectServiceImplUpdate implements ProjectService {

    @Override
    public List<User> getMembers(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Task> getTasks(Project project) {
        // TODO Auto-generated method stub
        return null;
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
    public List<Project> findByNameAndOrganization(String name, Organization organization) {
        // TODO Auto-generated method stub
        return null;
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

}
