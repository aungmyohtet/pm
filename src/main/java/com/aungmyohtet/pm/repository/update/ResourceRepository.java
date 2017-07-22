package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.entity.Organization;

public interface ResourceRepository {

    void save(Resource resource);

    List<Resource> findAll();

    List<Resource> findByOrganization(Organization organization);

    List<Resource> findByOrganizationName(String organizationName);

    void delete(Resource resource);

    Resource findById(int id);

}
