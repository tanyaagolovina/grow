package com.tetianaholovina.weatherforecast.model.forecast;

public class ForecastDetails {

    private String description;

    private double windSpeed;

    private double clouds;


    public ForecastDetails() {
    }

    public ForecastDetails(String description, double windSpeed, double clouds) {
        this.description = description;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return  description +
                ", windSpeed: " + windSpeed +
                ", clouds: " + clouds;
    }
}
