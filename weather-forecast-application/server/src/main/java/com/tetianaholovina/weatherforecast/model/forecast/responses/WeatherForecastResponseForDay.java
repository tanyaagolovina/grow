package com.tetianaholovina.weatherforecast.model.forecast.responses;

import com.tetianaholovina.weatherforecast.model.forecast.ForecastDetails;
import com.tetianaholovina.weatherforecast.model.forecast.LocationDetails;
import com.tetianaholovina.weatherforecast.model.forecast.Temperature;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherForecastResponseForDay extends ForecastResponse {

    private Temperature minTemperature;

    private Temperature maxTemperature;

    private ForecastDetails forecastDetails;

    public WeatherForecastResponseForDay() {}
}
