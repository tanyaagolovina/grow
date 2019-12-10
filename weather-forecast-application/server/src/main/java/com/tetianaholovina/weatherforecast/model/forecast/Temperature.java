package com.tetianaholovina.weatherforecast.model.forecast;

public class Temperature {

    private String temperature;

    private String time;

    public Temperature(String temperature, String time) {
        this.temperature = temperature;
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return temperature + " °C";
    }
}
