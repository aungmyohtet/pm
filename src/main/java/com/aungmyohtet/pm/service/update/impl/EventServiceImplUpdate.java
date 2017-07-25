package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.EventDto;
import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.EventRepository;
import com.aungmyohtet.pm.service.update.EventService;

@Service
public class EventServiceImplUpdate implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(Event event) {
        this.eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByOrganization(Organization organization) {
        return this.eventRepository.findByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByOrganizationName(String organizationName) {
        return this.eventRepository.findByOrganizationName(organizationName);
    }

    @Override
    @Transactional
    public void delete(Event event) {
        this.eventRepository.delete(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByTitle(String title) {
        return this.eventRepository.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByTitleAndOrganization(String title, Organization organization) {
        return this.eventRepository.findByTitleAndOrganization(title, organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findByTitleAndOrganizationName(String title, String organizationName) {
        return this.eventRepository.findByTitleAndOrganizationName(title, organizationName);
    }

    @Override
    @Transactional(readOnly = true)
    public Event findById(int id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

}
