package com.tetianaholovina.weatherforecast.dao.jpa;

import com.tetianaholovina.weatherforecast.dao.EventDao;
import com.tetianaholovina.weatherforecast.model.domain.Event;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.Set;

public interface EventRepository extends EventDao, CrudRepository<Event, Integer> {

    @Override
    Set<Event> findAllByIdAndDatetime(Integer id, String dateTime);

    @Override
    boolean existsById(Integer id);

    @Override
    void deleteById(Integer id);

    @Override
    Iterable<Event> findAll();

    @Override
    Event save(Event event);

    @Override
    Optional<Event> findById(Integer id);
}
