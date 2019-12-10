package com.tetianaholovina.weatherforecast.business;

import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import java.io.IOException;

public interface WeatherForecastBusiness {

    WeatherForecastResponseForNDays getWeatherForecastForNDays(String cityName, String countryName, int noOfDays) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException;
    CurrentWeatherForecastResponse getCurrentWeatherForecast(String cityName, String countryName) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException;
}
