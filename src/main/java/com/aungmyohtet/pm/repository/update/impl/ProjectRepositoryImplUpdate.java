package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.repository.update.ProjectRepository;

public class ProjectRepositoryImplUpdate implements ProjectRepository {

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

}
