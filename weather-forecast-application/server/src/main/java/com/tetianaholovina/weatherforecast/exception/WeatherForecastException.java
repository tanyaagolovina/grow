package com.tetianaholovina.weatherforecast.exception;

public class WeatherForecastException extends Exception{

    private static final long serialVersionUID = 1L;

    public WeatherForecastException(final String message) {
        super(message);
    }

    public WeatherForecastException(final String message, Throwable cause) {
        super(message, cause);
    }
}
