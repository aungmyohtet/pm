package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.UserRepository;

@Repository
public class UserRepositoryImplUpdate implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            this.entityManager.persist(user);
        } else {
            this.entityManager.merge(user);
        }
    }

    @Override
    public void delete(User user) {
        this.entityManager.remove(user);
    }

    @Override
    public List<User> findAll() {
        Query query = this.entityManager.createQuery("SELECT u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        Query query = this.entityManager.createQuery("SELECT u from User u WHERE u.email = ?", User.class);
        query.setParameter(1, email);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            // to log
        }
        return user;
    }

    @Override
    public List<User> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.organizationMembers m WHERE m.organization = ?", User.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

    @Override
    public List<User> findByProject(Project project) {
        Query query = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.projectMembers m WHERE m.project = ?", User.class);
        query.setParameter(1, project);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> filterByEmailContainingText(String text) {
        return null;
    }

    @Override
    public List<User> findByOrganizationAndRole(Organization organization, Role role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByProjectAndRole(Project project, Role role) {
        // TODO Auto-generated method stub
        return null;
    }

}
