package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.OrganizationTeam;
import com.aungmyohtet.pm.repository.OrganizationTeamRepository;

@Repository
public class OrganizationTeamRepositoryImpl implements OrganizationTeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(OrganizationTeam organizationTeam) {

        entityManager.persist(organizationTeam);

    }

    @Override
    public OrganizationTeam findById(int id) {
        return entityManager.find(OrganizationTeam.class, id);
    }

    @Override
    public List<OrganizationTeam> findTeamByOrganizationId(int organizationId) {
        Query query = entityManager.createQuery("SELECT ot FROM OrganizationTeam ot WHERE ot.organization.id = ?", OrganizationTeam.class);
        query.setParameter(1, organizationId);
        return query.getResultList();
    }

}
