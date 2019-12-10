package com.tetianaholovina.weatherforecast.model.forecast.responses;

import com.tetianaholovina.weatherforecast.model.forecast.LocationDetails;

import java.time.LocalDateTime;

public abstract class ForecastResponse {

    protected LocalDateTime invocationDateTime;

    protected LocationDetails locationDetails;

    public ForecastResponse() {
    }

    public ForecastResponse(LocalDateTime invocationDateTime, LocationDetails locationDetails) {
        this.invocationDateTime = invocationDateTime;
        this.locationDetails = locationDetails;
    }

    public LocalDateTime getInvocationDateTime() {
        return invocationDateTime;
    }

    public void setInvocationDateTime(LocalDateTime invocationDateTime) {
        this.invocationDateTime = invocationDateTime;
    }

    public LocationDetails getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
    }
}
