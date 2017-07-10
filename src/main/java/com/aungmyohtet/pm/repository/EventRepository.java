package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;

public interface EventRepository {

	List<Event> findEventsOfOrganization(String organizationName);

	Organization findByName(String organizationName);

	void add(Organization organization);

    Event findByEventName(String eventName);

}
