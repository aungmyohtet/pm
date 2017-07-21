package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.EventRepository;

public class EventRepositoryImplUpdate implements EventRepository {

    @Override
    public void save(Event event) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Event> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> findByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> findByOrganizationName(String organizationName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Event event) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Event> findByTitle(String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> findByTitleAndOrganization(String title, Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Event> findByTitleAndOrganizationName(String title, String organizationName) {
        // TODO Auto-generated method stub
        return null;
    }

}
