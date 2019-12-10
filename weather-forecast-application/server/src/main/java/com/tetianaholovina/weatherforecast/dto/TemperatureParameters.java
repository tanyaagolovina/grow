package com.tetianaholovina.weatherforecast.dto;

import java.time.LocalDate;

public class TemperatureParameters extends AbstractForecastParameters {

    private double temperature;

    public TemperatureParameters(LocalDate localDate, double temperature) {
        super(localDate);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
