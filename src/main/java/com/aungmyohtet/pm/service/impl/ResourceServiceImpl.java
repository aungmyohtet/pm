package com.aungmyohtet.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.repository.ResourceRepository;

import com.aungmyohtet.pm.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional
    public void save(Resource resource) {
        resourceRepository.save(resource);
    }

    @Override
    @Transactional
    public List<Resource> findResourceByOrganizationId(int organizationId) {
        return resourceRepository.findResourceByOrganizationId(organizationId);
    }
    
    @Override
    @Transactional
    public Resource findById(int resourceId)
    {
        return resourceRepository.findById(resourceId);
    }
}
