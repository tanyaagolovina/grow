package com.tetianaholovina.weatherforecast.model.forecast;

import java.time.LocalDate;

public class AverageWeatherForecast {

    private LocalDate date;

    private Temperature avgDailyTemperature;

    private Temperature avgNightTemperature;

    private String avgPressure;


    public AverageWeatherForecast() {
    }

    public AverageWeatherForecast(LocalDate date, Temperature avgDailyTemperature, Temperature avgNightTemperature, String avgPressure) {
        this.date = date;
        this.avgDailyTemperature = avgDailyTemperature;
        this.avgNightTemperature = avgNightTemperature;
        this.avgPressure = avgPressure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Temperature getAvgDailyTemperature() {
        return avgDailyTemperature;
    }

    public void setAvgDailyTemperature(Temperature avgDailyTemperature) {
        this.avgDailyTemperature = avgDailyTemperature;
    }

    public Temperature getAvgNightTemperature() {
        return avgNightTemperature;
    }

    public void setAvgNightTemperature(Temperature avgNightTemperature) {
        this.avgNightTemperature = avgNightTemperature;
    }

    public String getAvgPressure() {
        return avgPressure;
    }

    public void setAvgPressure(String avgPressure) {
        this.avgPressure = avgPressure;
    }
}
