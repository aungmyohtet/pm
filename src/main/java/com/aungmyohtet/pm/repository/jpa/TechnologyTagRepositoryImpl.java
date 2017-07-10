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
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.repository.TechnologyTagRepository;

@Repository
public class TechnologyTagRepositoryImpl implements TechnologyTagRepository{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void save(TechnologyTag technologyTag) {
        if (technologyTag.isNew()) {
            entityManager.persist(technologyTag);
        } else {
            entityManager.merge(technologyTag);
        }
    }

    @Override
    public TechnologyTag findById(int id) {
        return entityManager.find(TechnologyTag.class, id);
    }

    @Override
    public List<TechnologyTag> findAll() {
        return entityManager.createQuery("SELECT t FROM TechnologyTag t", TechnologyTag.class).getResultList();
    }

    @Override
    public Set<TechnologyTag> find(Task task) {
        Query q = entityManager.createQuery("select t from TechnologyTag t JOIN t.task taskList WHERE taskList.id=?");
        q.setParameter(1, task.getId());
        List<TechnologyTag> technologyTags = q.getResultList();
        Set<TechnologyTag> technologyTag = new HashSet<TechnologyTag>(technologyTags);
        return technologyTag;
    }
}
