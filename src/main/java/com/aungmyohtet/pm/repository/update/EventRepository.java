package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;

public interface EventRepository {

    void save(Event event);

    List<Event> findAll();

    List<Event> findByOrganization(Organization organization);

    List<Event> findByOrganizationName(String organizationName);

    void delete(Event event);

    List<Event> findByTitle(String title);

    List<Event> findByTitleAndOrganization(String title, Organization organization);

    List<Event> findByTitleAndOrganizationName(String title, String organizationName);

    Event findById(int id);

}
