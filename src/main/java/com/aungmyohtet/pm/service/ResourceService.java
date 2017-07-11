package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.Resource;

public interface ResourceService {

    void save(Resource resource);

    List<Resource> findResourceByOrganizationId(int organizationId);
    
    Resource findById(int resourceId);
}
