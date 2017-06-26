package com.aungmyohtet.pm.repository;

import com.aungmyohtet.pm.entity.Organization;

public interface OrganizationRepository {

    void save(Organization organization);

    Organization findById(int id);
}
