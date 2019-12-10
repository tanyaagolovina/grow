package com.tetianaholovina.weatherforecast.service.forecast;

import com.tetianaholovina.weatherforecast.model.forecast.WeatherForecastRequest;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;

public interface WeatherService {
    String fetchWeatherForecastParams(WeatherForecastRequest weatherForecastRequest) throws DataNotFoundException, WeatherForecastException, UnauthorizedException;
    /*String fetchCurrentWeatherForecastParams(WeatherForecastRequest weatherForecastRequest) throws DataNotFoundException, WeatherForecastException, UnauthorizedException;*/
}
