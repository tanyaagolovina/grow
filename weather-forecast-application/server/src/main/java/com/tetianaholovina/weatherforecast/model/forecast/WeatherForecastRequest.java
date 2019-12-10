package com.tetianaholovina.weatherforecast.model.forecast;

public class WeatherForecastRequest {

    private String cityName;

    private String countryName;

    private String responseContentType;

    public WeatherForecastRequest() {
    }

    public WeatherForecastRequest(String cityName, String countryName, String responseContentType) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.responseContentType = responseContentType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }
}
