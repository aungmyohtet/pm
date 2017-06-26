package com.aungmyohtet.pm.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.OrganizationRepository;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Organization organization) {
	if (organization.getId() == null) {
	        entityManager.persist(organization);
	} else {
	    entityManager.merge(organization);
	}
    }

    @Override
    public Organization findById(int id) {
	return entityManager.find(Organization.class, id);
    }

}
