package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
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
        Query query = entityManager.createQuery("SELECT MAX(t.no) FROM Task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.id = ? AND p.name = ?");
        query.setParameter(1, organizationId);
        query.setParameter(2, projectName);
        Integer no = 0;
        try {
            no = (Integer) query.getSingleResult();
        } catch (Exception e) {

        }
        return no;
    }

    @Override
    public Task find(int organizationId, String projectName, int taskNo) {
        Query query = entityManager.createQuery("SELECT t FROM Task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.id = ? AND p.name = ? AND t.no = ?");
        query.setParameter(1, organizationId);
        query.setParameter(2, projectName);
        query.setParameter(3, taskNo);
        Task task = null;
        try {
            task = (Task) query.getSingleResult();
        } catch (Exception e) {

        }
        return task;
    }

    @Override
    public Integer findTaskMaxNoByOrganizationNameAndProjectName(String organizationName, String projectName) {
        Query query = entityManager.createQuery("SELECT MAX(t.no) FROM Task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.name = ? AND p.name = ?");
        query.setParameter(1, organizationName);
        query.setParameter(2, projectName);
        Integer no = 0;
        try {
            no = (Integer) query.getSingleResult();
        } catch (Exception e) {

        }
        return no;
    }

    @Override
    public Task find(String organizationName, String projectName, int taskNo) {
        Query query = entityManager.createQuery("SELECT t FROM Task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.name = ? AND p.name = ? AND t.no = ?");
        query.setParameter(1, organizationName);
        query.setParameter(2, projectName);
        query.setParameter(3, taskNo);
        Task task = null;
        try {
            task = (Task) query.getSingleResult();
        } catch (Exception e) {

        }
        return task;
    }

    @Override
    public List<Task> find(String organizationName, String projectName) {
        Query query = entityManager.createQuery("SELECT t FROM Task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.name = ? AND p.name = ?", Task.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, projectName);
        return query.getResultList();
    }

    @Override
    public List<TaskNote> findTaskNotes(String organizationName, String projectName, int taskNo) {
        Query query = entityManager.createQuery(
                "SELECT n FROM TaskNote n " + "JOIN n.task t " + "JOIN t.project p " + "JOIN p.organization o " + "WHERE o.name = ? AND p.name = ? AND t.no = ?", TaskNote.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, projectName);
        query.setParameter(3, taskNo);
        return query.getResultList();
    }

}
