package com.tetianaholovina.weatherforecast.service.schedule.impl;

import com.tetianaholovina.weatherforecast.dao.EventDao;
import com.tetianaholovina.weatherforecast.dao.jpa.EventRepository;
import com.tetianaholovina.weatherforecast.model.domain.Event;
import com.tetianaholovina.weatherforecast.service.schedule.EventService;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventDao = eventRepository;
    }

    @Override
    public Event saveEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Optional<Event> getById(int id) {
        return eventDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        if (eventDao.existsById(id)) {
            eventDao.deleteById(id);
        }
    }

    @Override
    public Set<Event> findAll() {
        Set<Event> events = new HashSet<>();
        eventDao.findAll().forEach(events::add);
        return events;
    }
}
