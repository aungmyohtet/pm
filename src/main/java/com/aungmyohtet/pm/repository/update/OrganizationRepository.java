package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;

public interface OrganizationRepository {

    void save(Organization organization);

    List<OrganizationRepository> findAll();

    void delete(Organization organization);

    Organization findByName(String name);

    Organization findById(int id);

}
