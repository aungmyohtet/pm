package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.ProjectTeam;
import com.aungmyohtet.pm.repository.ProjectTeamRepository;

@Repository
public class ProjectTeamRepositoryImpl implements ProjectTeamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProjectTeam> findByProjectNameAndOrganizationName(String orgnaizationName, String projectName) {
        Query query = entityManager.createQuery("SELECT t FROM ProjectTeam t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.name = ? AND p.name = ?",
                ProjectTeam.class);
        query.setParameter(1, orgnaizationName);
        query.setParameter(2, projectName);
        return query.getResultList();
    }

    @Override
    public void save(ProjectTeam projectTeam) {

        if (projectTeam.getId() == null) {
            entityManager.persist(projectTeam);
        } else {
            entityManager.merge(projectTeam);
        }

    }

}
