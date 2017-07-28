package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.repository.update.ResourceRepository;
import com.aungmyohtet.pm.service.update.ResourceService;

@Service
public class ResourceServiceImplUpdate implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional
    public void save(Resource resource) {
        this.resourceRepository.save(resource);
    }

    @Override
    public List<Resource> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Resource> findByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Resource> findByOrganizationName(String organizationName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Resource resource) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional(readOnly = true)
    public Resource findById(int id) {
        return this.resourceRepository.findById(id);
    }

}
