package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Resource;

public interface ResourceRepository {
    void save(Resource resource);

    List<Resource> findResourceByOrganizationId(int organizationId);

    Resource findById(int resourceId);
}
