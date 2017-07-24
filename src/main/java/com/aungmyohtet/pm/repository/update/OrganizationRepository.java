package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.User;

public interface OrganizationRepository {

    void save(Organization organization);

    List<Organization> findAll();

    void delete(Organization organization);

    Organization findByName(String name);

    Organization findById(int id);

    List<Organization> findByCreator(User user);

    List<Organization> findByMember(User user);

}
