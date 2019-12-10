package com.tetianaholovina.weatherforecast.service.schedule;

import com.tetianaholovina.weatherforecast.model.domain.Event;

import java.util.Optional;
import java.util.Set;

public interface EventService {

        Event saveEvent(Event event);

        Optional<Event> getById(int id);

        void deleteById(int id);

        Set<Event> findAll();
}
