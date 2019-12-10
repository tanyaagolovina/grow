package com.tetianaholovina.weatherforecast.model.forecast.responses;



import com.tetianaholovina.weatherforecast.model.forecast.AverageWeatherForecast;
import com.tetianaholovina.weatherforecast.model.forecast.LocationDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastResponseForNDays extends ForecastResponse {

    private List<AverageWeatherForecast> averageWeatherForecastForNDays;

    public WeatherForecastResponseForNDays() {
        averageWeatherForecastForNDays = new ArrayList<>();
    }

    public WeatherForecastResponseForNDays(LocalDateTime invocationDateTime, LocationDetails locationDetails, List<AverageWeatherForecast> averageWeatherForecastForNDays) {
        super(invocationDateTime, locationDetails);
        this.averageWeatherForecastForNDays = averageWeatherForecastForNDays;
    }

    public List<AverageWeatherForecast> getAverageWeatherForecastForNDays() {
        return averageWeatherForecastForNDays;
    }

    public void setAverageWeatherForecastForNDays(List<AverageWeatherForecast> averageWeatherForecastForNDays) {
        this.averageWeatherForecastForNDays = averageWeatherForecastForNDays;
    }

}
