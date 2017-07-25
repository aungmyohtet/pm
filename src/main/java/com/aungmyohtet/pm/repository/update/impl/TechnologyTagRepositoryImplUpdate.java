package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Task;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.repository.update.TechnologyTagRepository;

@Repository
public class TechnologyTagRepositoryImplUpdate implements TechnologyTagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TechnologyTag technologyTag) {
        if (technologyTag.getId() == null) {
            this.entityManager.persist(technologyTag);
        } else {
            this.entityManager.merge(technologyTag);
        }
    }

    @Override
    public void delete(TechnologyTag technologyTag) {
        this.entityManager.remove(technologyTag);
    }

    @Override
    public List<TechnologyTag> findAll() {
        Query query = this.entityManager.createQuery("SELECT t from TechnologyTag t", TechnologyTag.class);
        return query.getResultList();
    }

    @Override
    public List<TechnologyTag> findByTask(Task task) {
        Query query = this.entityManager.createQuery("SELECT t from TechnologyTag t JOIN FETCH t.task task WHERE task = ?", TechnologyTag.class);
        return query.getResultList();
    }

    @Override
    public TechnologyTag findByName(String name) {
        Query query = this.entityManager.createQuery("SELECT t from TechnologyTag t WHERE t.name = ?", TechnologyTag.class);
        query.setParameter(1, name);
        TechnologyTag technologyTag = null;
        try {
            technologyTag = (TechnologyTag) query.getSingleResult();
        } catch (Exception e) {
            //
        }
        return technologyTag;
    }

    @Override
    public TechnologyTag findById(int id) {
        return this.entityManager.find(TechnologyTag.class, id);
    }

    @Override
    public List<TechnologyTag> filterByNameContainingText(String text) {
        return null;
    }

}
