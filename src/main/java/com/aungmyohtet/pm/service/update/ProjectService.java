package com.aungmyohtet.pm.service.update;

import java.util.Date;
import java.util.List;

import com.aungmyohtet.pm.dto.ProjectDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.ProjectMember;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.User;

public interface ProjectService {

    List<User> getMembers(Project project);

    List<Task> getTasks(Project project);

    List<ProjectMember> getProjectMembers(Project project);

    List<User> getMembersByRole(Project project, Role role);

    List<Task> getTasksBetweenDate(Project project, Date start, Date end);

    List<Task> getTasksByAssignee(Project project, User user);

    void addTask(Project project, Task task);

    void addMember(Project project, User user);

    // to write searching methods

    // wrap normal repository methods
    void save(Project project);

    List<Project> findAll();

    List<Project> findByOrganization(Organization organization);

    List<Project> findByOrganizationName(String organizationName);

    void delete(Project project);

    List<Project> findByName(String name);

    Project findByNameAndOrganization(String name, Organization organization);

    List<Project> findByNameAndOrganizationName(String name, String organizationName);

    Project findById(int id);

    ProjectDto convertToDto(Project project);
}
