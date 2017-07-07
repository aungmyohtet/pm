package com.aungmyohtet.pm.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.service.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/{organizationName}/events", method = RequestMethod.GET)
    public String showEvents(@PathVariable("organizationName") String organizationName, Model model, HttpServletRequest request) {
        List<Event> events = eventService.findEventsOfOrganization(organizationName);
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("events", events.stream().map(event -> eventService.convertToDto(event)).collect(Collectors.toList()));
        return "eventList";
    }

    @RequestMapping(value = "/{organizationName}/events/new", method = RequestMethod.GET)
    public String showOrganizationMemberForm(@PathVariable("organizationName") String organizationName, Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("organizationName", organizationName);
        return "eventForm";
    }

    @RequestMapping(value = "/{organizationName}/events/new", method = RequestMethod.POST)
    public String addEventToOrganization(@PathVariable("organizationName") String organizationName, Model model, @ModelAttribute Event event) {
        eventService.addEventToOrganization(event, organizationName);
        return "redirect:/" + organizationName + "/events";
    }

}
