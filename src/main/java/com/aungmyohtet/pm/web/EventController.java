package com.aungmyohtet.pm.web;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @ModelAttribute("module")
    String module() {
        return "events";
    }

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
    public String addEventToOrganization(@PathVariable("organizationName") String organizationName, Model model, @ModelAttribute Event event, BindingResult result) {

        String eventName = event.getTitle();
        Date eventStartDate = event.getStart();
        Date eventEndDate = event.getEnd();

        if (eventName.isEmpty()) {
            String eventNameError = "Please Fill Title of Event";
            model.addAttribute("eventNameError", eventNameError);
            model.addAttribute("organizationName", organizationName);

            return "eventForm";
        }

        if (eventStartDate == null || eventEndDate == null) {
            String dateEmptyError = "Please Fill Every Date";
            model.addAttribute("dateEmptyError", dateEmptyError);
            model.addAttribute("organizationName", organizationName);

            return "eventForm";
        }
        if ((eventStartDate.compareTo(eventEndDate) > 0)) {

            String dateError = "Event End Date Must Be Greater Than Event Start Date.";

            model.addAttribute("dateError", dateError);
            model.addAttribute("organizationName", organizationName);

            return "eventForm";
        } else {

            eventService.addEventToOrganization(event, organizationName);

            return "redirect:/" + organizationName + "/events";

        }
    }

    @RequestMapping(value = "/{organizationName}/events/{eventName}", method = RequestMethod.GET)
    public String showEventDetail(@PathVariable("organizationName") String organizationName, @PathVariable("eventName") String eventName, Model model) {
        
        Event eventDetail = eventService.findByEventName(eventName);   
        model.addAttribute("eventDetail",eventDetail);
        model.addAttribute("organizationName",organizationName);

        return "eventDetail";
    }
}
