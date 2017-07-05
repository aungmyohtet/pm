package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.Event;

public interface EventService {

	List<Event> findEventsOfOrganization(String organizationName);

	void addEventToOrganization(Event event, String organizationName);

}