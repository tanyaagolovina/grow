package com.tetianaholovina.weatherforecast.service.forecast;

import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import java.io.IOException;

public interface DataProcessService {
    WeatherForecastResponseForNDays processWeatherDataForNDays(String jsonData, int noOfDays) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException;

    CurrentWeatherForecastResponse processCurrentWeatherData(String jsonData) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException;
}
