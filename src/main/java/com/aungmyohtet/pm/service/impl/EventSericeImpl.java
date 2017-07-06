package com.aungmyohtet.pm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.EventDto;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.EventRepository;
import com.aungmyohtet.pm.service.EventService;

@Service
public class EventSericeImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRepository organizationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Event> findEventsOfOrganization(String organizationName) {
        return eventRepository.findEventsOfOrganization(organizationName);
    }

    @Override
    @Transactional
    public void addEventToOrganization(Event event, String organizationName) {

        Organization organization = organizationRepository.findByName(organizationName);
        organization.getEvents().add(event);
        event.setOrganization(organization);
        eventRepository.add(organization);
    }

    @Override
    public EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

}
