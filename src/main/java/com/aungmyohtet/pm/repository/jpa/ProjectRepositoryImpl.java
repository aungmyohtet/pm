package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.repository.ProjectRepository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Project project) {
        if (project.getId() == null) {
            entityManager.persist(project);
        } else {
            entityManager.merge(project);
        }
    }

    @Override
    public Project findById(int id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findByOrganization(int organizationId) {
        Query query = entityManager.createQuery("SELECT p FROM Project p WHERE p.organization.id = ?", Project.class);
        query.setParameter(1, organizationId);
        return query.getResultList();
    }

    @Override
    public Project findByOrganizationIdAndProjectName(int organizationId, String projectName) {
        Query query = entityManager.createQuery("SELECT p FROM Project p WHERE p.organization.id = ? AND p.name = ?");
        query.setParameter(1, organizationId);
        query.setParameter(2, projectName);
        Project project = null;
        try {
            return (Project) query.getSingleResult();
        } catch (Exception e) {

        }
        return project;
    }

    @Override
    public List<Project> findByOrganizationName(String organizationName) {
        Query query = entityManager.createQuery("SELECT p FROM Project p WHERE p.organization.name = ?", Project.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public Project findByOrganizationNameAndProjectName(String organizationName, String projectName) {
        Query query = entityManager.createQuery("SELECT p FROM Project p WHERE p.organization.name = ? AND p.name = ?");
        query.setParameter(1, organizationName);
        query.setParameter(2, projectName);
        Project project = null;
        try {
            return (Project) query.getSingleResult();
        } catch (Exception e) {

        }
        return project;
    }

}
