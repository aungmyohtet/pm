package com.aungmyohtet.pm.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
