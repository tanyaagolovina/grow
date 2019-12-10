package com.tetianaholovina.weatherforecast.business;

import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.domain.Plan;
import java.io.IOException;
import java.util.List;

public interface EventBusiness {

    List<Plan> getAllEvents();

    Plan createEvent(String title, String address, String dateAndTime, String usersEmail) throws DataNotFoundException, WeatherForecastException, UnauthorizedException, IOException;
}
