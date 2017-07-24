package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.repository.update.TaskRepository;

@Repository
public class TaskRepositoryImplUpdate implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            this.entityManager.persist(task);
        } else {
            this.entityManager.merge(task);
        }
    }

    @Override
    public List<Task> findAll() {
        Query query = this.entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    @Override
    public void delete(Task task) {
        this.entityManager.remove(task);
    }

    @Override
    public Task findById(int id) {
        return this.entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findByProject(Project project) {
        Query query = this.entityManager.createQuery("SELECT t FROM Task t WHERE t.project = ?", Task.class);
        query.setParameter(1, project);
        return query.getResultList();
    }

    @Override
    public Task findByTitleAndProject(String title, Project project) {
        Query query = this.entityManager.createQuery("SELECT t FROM Task t WHERE t.project = ? and t.title = ?", Task.class);
        query.setParameter(1, project);
        query.setParameter(2, title);
        Task task = null;
        try {
            task = (Task) query.getSingleResult();
        } catch (Exception e) {
            // to log
        }
        return task;
    }

    @Override
    public Task findByNoAndProject(int no, Project project) {
        Query query = this.entityManager.createQuery("SELECT t FROM Task t WHERE t.project = ? and t.no = ?", Task.class);
        query.setParameter(1, project);
        query.setParameter(2, no);
        Task task = null;
        try {
            task = (Task) query.getSingleResult();
        } catch (Exception e) {
            // to log
        }
        return task;
    }

}
