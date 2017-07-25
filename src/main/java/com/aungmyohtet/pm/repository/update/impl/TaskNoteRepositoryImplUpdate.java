package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.repository.update.TaskNoteRepository;

@Repository
public class TaskNoteRepositoryImplUpdate implements TaskNoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TaskNote taskNote) {
        if (taskNote.getId() == null) {
            this.entityManager.persist(taskNote);
        } else {
            this.entityManager.merge(taskNote);
        }
    }

    @Override
    public List<TaskNote> findAll() {
        Query query = this.entityManager.createQuery("SELECT t from TaskNote t", TaskNote.class);
        return query.getResultList();
    }

    @Override
    public void delete(TaskNote taskNote) {
        this.entityManager.remove(taskNote);
    }

    @Override
    public TaskNote findById(int id) {
        return this.entityManager.find(TaskNote.class, id);
    }

    @Override
    public List<TaskNote> findByTask(Task task) {
        Query query = this.entityManager.createQuery("SELECT t FROM TaskNote t WHERE t.task = ?", TaskNote.class);
        query.setParameter(1, task);
        return query.getResultList();
    }

}
