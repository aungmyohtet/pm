package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.EventRepository;

@Repository
public class EventRepositoryImplUpdate implements EventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Event event) {
        if (event.getId() == null) {
            this.entityManager.persist(event);
        } else {
            this.entityManager.merge(event);
        }
    }

    @Override
    public List<Event> findAll() {
        Query query = this.entityManager.createQuery("select e from Event e", Event.class);
        return query.getResultList();

    }

    @Override
    public List<Event> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("select e from Event e where e.organization = ?", Event.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

    @Override
    public List<Event> findByOrganizationName(String organizationName) {
        Query query = this.entityManager.createQuery("select e from Event e where e.organization.name = ?", Event.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public void delete(Event event) {
        this.entityManager.remove(event);
    }

    @Override
    public List<Event> findByTitle(String title) {
        Query query = this.entityManager.createQuery("select e from Event e where e.title = ?", Event.class);
        query.setParameter(1, title);
        return query.getResultList();    }

    @Override
    public List<Event> findByTitleAndOrganization(String title, Organization organization) {
        Query query = this.entityManager.createQuery("select e from Event e where e.organization = ? and e.title = ?", Event.class);
        query.setParameter(1, organization);
        query.setParameter(2, title);
        return query.getResultList();
    }

    @Override
    public List<Event> findByTitleAndOrganizationName(String title, String organizationName) {
        Query query = this.entityManager.createQuery("select e from Event e where e.organization.name = ? and e.title = ?", Event.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, title);
        return query.getResultList();    }

    @Override
    public Event findById(int id) {
        return this.entityManager.find(Event.class, id);
    }

}
