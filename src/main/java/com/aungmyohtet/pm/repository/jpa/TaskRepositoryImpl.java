package com.aungmyohtet.pm.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.repository.TaskRepository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            entityManager.persist(task);
        } else {
            entityManager.merge(task);
        }
    }

    @Override
    public Task findById(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public Integer findTaskMaxNoByOrganizationAndProject(int organizationId, String projectName) {
        Query query = entityManager.createQuery("SELECT MAX(t.no) FROM Task t "
                + "JOIN t.project p "
                + "JOIN p.organization o "
                + "WHERE o.id = ? AND p.name = ?");
        query.setParameter(1, organizationId);
        query.setParameter(2, projectName);
        Integer no = 0;
        try {
            no = (Integer) query.getSingleResult();
        } catch(Exception e) {

        }
        return no;
    }

}
