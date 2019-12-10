package com.tetianaholovina.weatherforecast.model.forecast;

import java.time.LocalDate;

public class WeatherData {

    private LocalDate date;

    private Temperature temperature;

    private ForecastDetails forecastDetails;

    public WeatherData() {
    }

    public WeatherData(Temperature temperature, ForecastDetails forecastDetails) {
        this.temperature = temperature;
        this.forecastDetails = forecastDetails;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public ForecastDetails getForecastDetails() {
        return forecastDetails;
    }

    public void setForecastDetails(ForecastDetails forecastDetails) {
        this.forecastDetails = forecastDetails;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
