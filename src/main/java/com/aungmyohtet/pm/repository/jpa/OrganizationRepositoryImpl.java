package com.aungmyohtet.pm.repository.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;
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

    @Override
    public Organization findByIdFetchingMembers(int id) {
        Query q = entityManager.createQuery("SELECT o FROM Organization o JOIN FETCH o.organizationMembers i WHERE o.id = :id");
        q.setParameter("id", id);
        Organization organization = null;
        try {
            organization = (Organization) q.getSingleResult();
        } catch(NoResultException e) {
            
        }
        return organization;
    }

    @Override
    public List<OrganizationMember> findMembersByOrganization(int id) {
        Query query = entityManager.createQuery("select m from OrganizationMember m where m.organization.id = :id", OrganizationMember.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Project> findProjectsByOrganization(int id) {
        Query query = entityManager.createQuery("select p from Project p where p.organization.id = :id", Project.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Organization findByName(String name) {
        Query query = entityManager.createQuery("select o from Organization o where o.name = :name", Organization.class);
        query.setParameter("name", name);
        Organization organization = null;
        try {
            organization = (Organization) query.getSingleResult();
        } catch (Exception e) {
        }
        return organization;
    }

    @Override
    public Organization findByNameFetchingMembers(String name) {
        Query q = entityManager.createQuery("SELECT o FROM Organization o JOIN FETCH o.organizationMembers i WHERE o.name = :name");
        q.setParameter("name", name);
        Organization organization = null;
        try {
            organization = (Organization) q.getSingleResult();
        } catch(NoResultException e) {
        }
        return organization;
    }

    @Override
    public List<OrganizationMember> findMembersByOrganizationName(String name) {
        Query query = entityManager.createQuery("select m from OrganizationMember m where m.organization.name = :name", OrganizationMember.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Project> findProjectsByOrganizationName(String name) {
        Query query = entityManager.createQuery("select p from Project p where p.organization.name = :name", Project.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

}
