package com.tetianaholovina.weatherforecast.exception;

public class UnauthorizedException extends Exception{
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(final String message) {
        super(message);
    }
}
