package com.aungmyohtet.pm.repository.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.repository.StatusRepository;

@Repository
public class StatusRepositoryImpl implements StatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Status status) {
        if (status.isNew()) {
            entityManager.persist(status);
        } else {
            entityManager.merge(status);
        }
    }

    @Override
    public Status findById(int id) {
        return entityManager.find(Status.class, id);
    }

    @Override
    public List<Status> findAll() {
        return entityManager.createQuery("SELECT s FROM Status s", Status.class).getResultList();
    }

    @Override
    public Set<Status> find(Task task) {
        Query q = entityManager.createQuery("select s from Status s JOIN s.task taskList WHERE taskList.id=?");
        q.setParameter(1, task.getId());
        List<Status> statusList = q.getResultList();
        Set<Status> items = new HashSet<Status>(statusList);
        /*
         * @SuppressWarnings("unchecked") Set<Status> statusNames = (Set<Status>) q.getResultList();
         */
        return items;
    }
}
