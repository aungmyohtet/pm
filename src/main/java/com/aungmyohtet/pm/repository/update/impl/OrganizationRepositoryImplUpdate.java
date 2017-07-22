package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.OrganizationRepository;

@Repository
public class OrganizationRepositoryImplUpdate implements OrganizationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Organization organization) {
        if (organization.getId() == null) {
            this.entityManager.persist(organization);
        } else {
            this.entityManager.merge(organization);
        }
    }

    @Override
    public List<OrganizationRepository> findAll() {
        Query query = this.entityManager.createQuery("select o from Organization o", Organization.class);
        return query.getResultList();
    }

    @Override
    public void delete(Organization organization) {
        this.entityManager.remove(organization);
    }

    @Override
    public Organization findByName(String name) {
        Query query = this.entityManager.createQuery("select o from Organization o where o.name = ?", Organization.class);
        try {
            return (Organization) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Organization findById(int id) {
        return this.entityManager.find(Organization.class, id);
    }

}
