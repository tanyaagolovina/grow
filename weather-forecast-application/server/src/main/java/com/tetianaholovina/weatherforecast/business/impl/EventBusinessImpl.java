package com.tetianaholovina.weatherforecast.business.impl;

import com.tetianaholovina.weatherforecast.business.EventBusiness;
import com.tetianaholovina.weatherforecast.business.WeatherForecastBusiness;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.domain.Event;
import com.tetianaholovina.weatherforecast.model.domain.Plan;
import com.tetianaholovina.weatherforecast.model.domain.User;
import com.tetianaholovina.weatherforecast.service.schedule.EventService;
import com.tetianaholovina.weatherforecast.service.schedule.PlanService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Component
public class EventBusinessImpl implements EventBusiness {

    private EventService eventService;

    private PlanService planService;

    private WeatherForecastBusiness weatherForecastBusiness;

    private int rows = 0;

    private int planId = 12;

    public EventBusinessImpl(EventService eventService, PlanService planService, WeatherForecastBusiness weatherForecastBusiness) {
        this.eventService = eventService;
        this.planService = planService;
        this.weatherForecastBusiness = weatherForecastBusiness;
    }

    @Override
    @Transactional
    public List<Plan> getAllEvents() {
        return planService.findAllByUserId(getCurrentUser().getId());
    }

    @Override
    @Transactional
    public Plan createEvent(String title, String address, String dateAndTime, String usersEmail) throws DataNotFoundException, WeatherForecastException, UnauthorizedException, IOException {
        Event event = eventService.saveEvent(new Event(incrementRowId(), title, address, dateAndTime));

        //TODO add method implementation for fetching forecast for specific time - event time
        event.setForecast(weatherForecastBusiness.getCurrentWeatherForecast("Kyiv", "ua").toString());

        Plan plan = new Plan(++planId, getCurrentUser(), event);
        plan.setStatus("Created");

        planService.save(plan);

        return plan;
    }


    private User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private int incrementRowId(){
        if (rows == 0) {
            countLastRow();
            rows++;
        } else {
            rows++;
        }
        return rows;
    }

    private void countLastRow(){
        rows = eventService.findAll().size();
    }
}
