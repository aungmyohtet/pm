package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.repository.update.ResourceRepository;

@Repository
public class ResourceRepositoryImplUpdate implements ResourceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Resource resource) {
        if (resource.getId() == null) {
            this.entityManager.persist(resource);
        } else {
            this.entityManager.merge(resource);
        }
    }

    @Override
    public List<Resource> findAll() {
        Query query = this.entityManager.createQuery("select r from Resource r", Resource.class);
        return query.getResultList();
    }

    @Override
    public List<Resource> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("select r from Resource r where r.organization = ?", Resource.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

    @Override
    public List<Resource> findByOrganizationName(String organizationName) {
        Query query = this.entityManager.createQuery("select r from Resource r where r.organization.name = ?", Resource.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public void delete(Resource resource) {
        this.entityManager.remove(resource);
    }

    @Override
    public Resource findById(int id) {
        return this.entityManager.find(Resource.class, id);
    }

}
