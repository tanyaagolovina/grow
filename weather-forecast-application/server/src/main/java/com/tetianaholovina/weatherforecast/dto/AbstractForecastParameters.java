package com.tetianaholovina.weatherforecast.dto;

import java.time.LocalDate;

public abstract class AbstractForecastParameters {

    protected LocalDate localDate;

    public AbstractForecastParameters(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
