package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.User;

public interface UserRepository {

    void save(User user);

    User findByEmail(String email);

    List<Organization> findOrganizationsByUser(String email);

    List<Organization> findOrganizationsCreatedByUser(String email);

    List<Organization> findOrganizationsInvolvingUser(String email);

    List<User> findMembersOfOrganization(int id);

    List<User> findMembersOfPoject(Project project);
}
