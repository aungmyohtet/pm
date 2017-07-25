package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.repository.update.StatusRepository;

@Repository
public class StatusRepositoryImplUpdate implements StatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Status status) {
        if (status.getId() == null) {
            this.entityManager.persist(status);
        } else {
            this.entityManager.merge(status);
        }
    }

    @Override
    public void delete(Status status) {
        this.entityManager.remove(status);
    }

    @Override
    public List<Status> findAll() {
        Query query = this.entityManager.createQuery("SELECT s from Status s", Status.class);
        return query.getResultList();
    }

    @Override
    public Status findByName(String name) {
        Query query = this.entityManager.createQuery("SELECT s from Status s where s.name = ?", Status.class);
        query.setParameter(1, name);
        Status status = null;
        try {
            status = (Status) query.getSingleResult();
        } catch (Exception e) {
            // to log here
        }
        return status;
    }

    @Override
    public List<Status> filterByNameContainingText(String text) {
        return null;
    }

}
