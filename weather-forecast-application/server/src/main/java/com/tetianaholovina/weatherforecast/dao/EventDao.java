package com.tetianaholovina.weatherforecast.dao;

import com.tetianaholovina.weatherforecast.model.domain.Event;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface EventDao {
    Set<Event> findAllByIdAndDatetime(Integer id, String dateTime);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    Iterable<Event> findAll();

    Event save(Event event);

    Optional<Event> findById(Integer id);
}
