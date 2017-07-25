package com.aungmyohtet.pm.repository.update.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.RoleRepository;

@Repository
public class RoleRepositoryImplUpdate implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String DEFAULT_ROLE_NAME = "USER";

    private static final String ADMIN_ROLE_NAME = "ADMIN";

    @Override
    public void save(Role role) {
        if (role.getId() == null) {
            this.entityManager.persist(role);
        } else {
            this.entityManager.merge(role);
        }
    }

    @Override
    public Role findById(int id) {
        return this.entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        Query query = this.entityManager.createQuery("SELECT r FROM Role r where r.name = ?", Role.class);
        query.setParameter(1, name);
        Role role = null;
        try {
            role = (Role) query.getSingleResult();
        } catch (Exception e) {
            // to log
        }
        return role;
    }

    @Override
    public Role getDefaultRole() {
        return findByName(DEFAULT_ROLE_NAME);
    }

    @Override
    public Role getAdminRole() {
        return findByName(ADMIN_ROLE_NAME);
    }

    @Override
    public Role findByUserAndOrganization(User user, Organization organization) {
        Query query = this.entityManager.createQuery("SELECT r FROM User u JOIN FETCH "
                + "u.organizationMembers m JOIN FETCH m.role r WHERE u = ? AND m.organization = ?", Role.class);
        query.setParameter(1, user);
        query.setParameter(2, organization);
        Role role = null;
        try {
            role = (Role) query.getSingleResult();
        } catch (Exception e) {
            // to log here
        }
        return role;
    }

    @Override
    public Role findByUserAndProject(User user, Project project) {
        Query query = this.entityManager.createQuery("SELECT r FROM User u JOIN FETCH "
                + "u.projectMembers m JOIN FETCH m.role r WHERE u = ? AND m.project = ?", Role.class);
        query.setParameter(1, user);
        query.setParameter(2, project);
        Role role = null;
        try {
            role = (Role) query.getSingleResult();
        } catch (Exception e) {
            // to log here
        }
        return role;
    }

}
