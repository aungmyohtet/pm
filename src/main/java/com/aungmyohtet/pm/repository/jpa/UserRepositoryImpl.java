package com.aungmyohtet.pm.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email=?");
            query.setParameter(1, email);
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<Organization> findOrganizationsByUser(String email) {
        Query query = entityManager.createQuery("SELECT m FROM OrganizationMember m WHERE m.user.email=?");
        query.setParameter(1, email);
        List<OrganizationMember> members =  query.getResultList();
        List<Organization> organizations = new ArrayList<>();
        for (OrganizationMember member : members) {
            organizations.add(member.getOrganization());
        }
        return organizations;
    }

    @Override
    public List<Organization> findOrganizationsCreatedByUser(String email) {
        //Query query = entityManager.createQuery("SELECT m FROM OrganizationMember m "
                //+ "JOIN FETCH Organization o ");
        Query query = entityManager.createQuery("SELECT o FROM Organization o "
                + "JOIN FETCH o.organizationMembers om "
                + "JOIN FETCH om.user u "
                + "JOIN FETCH om.role r "
                + "WHERE u.email=? AND r.name=?", Organization.class);
        query.setParameter(1, email);
        query.setParameter(2, "MANAGER");
        /*List<OrganizationMember> members =  query.getResultList();
        List<Organization> organizations = new ArrayList<>();
        for (OrganizationMember member : members) {
            organizations.add(member.getOrganization());
        }*/
        //return organizations;
        return query.getResultList();
    }

    @Override
    public List<Organization> findOrganizationsInvolvingUser(String email) {
        Query query = entityManager.createQuery("SELECT o FROM Organization o "
                + "JOIN FETCH o.organizationMembers om "
                + "JOIN FETCH om.user u "
                + "JOIN FETCH om.role r "
                + "WHERE u.email=? AND r.name=?", Organization.class);
        query.setParameter(1, email);
        query.setParameter(2, "DEVELOPER");
        return query.getResultList();
    }

}
