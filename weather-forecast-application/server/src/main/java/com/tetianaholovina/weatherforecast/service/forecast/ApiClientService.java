package com.tetianaholovina.weatherforecast.service.forecast;

import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface ApiClientService {
    ResponseEntity<String> invokeGetResource(String uri, HttpHeaders httpHeaders) throws UnauthorizedException, DataNotFoundException, WeatherForecastException;
}
