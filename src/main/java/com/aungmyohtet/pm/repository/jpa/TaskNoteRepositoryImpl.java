package com.aungmyohtet.pm.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.TaskNoteRepository;

@Repository
public class TaskNoteRepositoryImpl implements TaskNoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TaskNote taskNote) {
        if (taskNote.isNew()) {
            entityManager.persist(taskNote);
        } else {
            entityManager.merge(taskNote);
        }
    }

    @Override
    public TaskNote findById(int id) {
        return entityManager.find(TaskNote.class, id);
    }

    @Override
    public List<TaskNote> findAll() {
        return null;
    }

    @Override
    public List<TaskNote> findByCommentedBy(User user) {
        Query query = entityManager.createQuery("SELECT t FROM TaskNote t WHERE t.commentedBy = :user", TaskNote.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public List<TaskNote> findByTask(Task task) {
        Query query = entityManager.createQuery("SELECT t FROM TaskNote t WHERE t.task = :task", TaskNote.class);
        query.setParameter("task", task);
        return query.getResultList();

    }

}
