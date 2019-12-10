package com.tetianaholovina.weatherforecast.dto;

import java.time.LocalDate;

public class PressureParameters extends AbstractForecastParameters {

    private double pressure;

    public PressureParameters(LocalDate localDate, double pressure) {
        super(localDate);
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
