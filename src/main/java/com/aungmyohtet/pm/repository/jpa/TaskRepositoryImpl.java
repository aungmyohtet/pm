package com.aungmyohtet.pm.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
