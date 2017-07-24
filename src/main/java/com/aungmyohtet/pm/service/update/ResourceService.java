package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Resource;

public interface ResourceService {

    // wrap normal repository methods
    void save(Resource resource);

    List<Resource> findAll();

    List<Resource> findByOrganization(Organization organization);

    List<Resource> findByOrganizationName(String organizationName);

    void delete(Resource resource);

    Resource findById(int id);
}
