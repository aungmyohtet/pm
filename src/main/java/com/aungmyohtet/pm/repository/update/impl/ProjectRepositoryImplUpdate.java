package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.repository.update.ProjectRepository;

@Repository
public class ProjectRepositoryImplUpdate implements ProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Project project) {
        if (project.getId() == null) {
            this.entityManager.persist(project);
        } else {
            this.entityManager.merge(project);
        }
    }

    @Override
    public List<Project> findAll() {
        Query query = this.entityManager.createQuery("select p from Project p", Project.class);
        return query.getResultList();
    }

    @Override
    public List<Project> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("SELECT p FROM Project p WHERE p.organization = ?", Project.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

    @Override
    public List<Project> findByOrganizationName(String organizationName) {
        Query query = this.entityManager.createQuery("select p from Project p where p.organization.name = ?", Project.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public void delete(Project project) {
        this.entityManager.remove(project);
    }

    @Override
    public List<Project> findByName(String name) {
        Query query = this.entityManager.createQuery("select p from Project p where p.name = ?", Project.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    @Override
    public List<Project> findByNameAndOrganization(String name, Organization organization) {
        Query query = this.entityManager.createQuery("select p from Project p where p.organization = ? and p.name = ?", Project.class);
        query.setParameter(1, organization);
        query.setParameter(2, name);
        return query.getResultList();
    }

    @Override
    public List<Project> findByNameAndOrganizationName(String name, String organizationName) {
        Query query = this.entityManager.createQuery("select p from Project p where p.organization.name = ? and p.name = ?", Project.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, name);
        return query.getResultList();
    }

    @Override
    public Project findById(int id) {
        return this.entityManager.find(Project.class, id);
    }

}
