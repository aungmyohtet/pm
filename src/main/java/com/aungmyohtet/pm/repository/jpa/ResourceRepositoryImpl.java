package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Resource;
import com.aungmyohtet.pm.repository.ResourceRepository;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Resource resource) {
        entityManager.persist(resource);
    }

    @Override
    public List<Resource> findResourceByOrganizationId(int organizationId) {
        Query query = entityManager.createQuery("SELECT r FROM Resource r WHERE r.organization.id = ?", Resource.class);
        query.setParameter(1, organizationId);
        return query.getResultList();
    }

    @Override
    public Resource findById(int resourceId) {
        return entityManager.find(Resource.class, resourceId);
    }
}
