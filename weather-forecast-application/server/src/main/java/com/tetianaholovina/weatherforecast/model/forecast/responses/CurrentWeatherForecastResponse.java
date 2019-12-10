package com.tetianaholovina.weatherforecast.model.forecast.responses;

import com.tetianaholovina.weatherforecast.model.forecast.WeatherData;
import com.tetianaholovina.weatherforecast.model.forecast.LocationDetails;

import java.time.LocalDateTime;

public class CurrentWeatherForecastResponse extends ForecastResponse{

    private WeatherData currentWeather;

    public CurrentWeatherForecastResponse() {
    }

    public CurrentWeatherForecastResponse(LocalDateTime invocationDateTime, LocationDetails locationDetails, WeatherData currentWeather) {
        super(invocationDateTime, locationDetails);
        this.currentWeather = currentWeather;
    }

    public WeatherData getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherData currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public String toString() {
        return currentWeather.getTemperature().toString() + ", " + currentWeather.getForecastDetails().toString();
    }
}
