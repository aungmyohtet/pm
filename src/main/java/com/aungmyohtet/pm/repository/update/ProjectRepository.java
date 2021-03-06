package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Organization;

public interface ProjectRepository {

    void save(Project project);

    List<Project> findAll();

    List<Project> findByOrganization(Organization organization);

    List<Project> findByOrganizationName(String organizationName);

    void delete(Project project);

    List<Project> findByName(String name);

    Project findByNameAndOrganization(String name, Organization organization);

    List<Project> findByNameAndOrganizationName(String name, String organizationName);

    Project findById(int id);

}
