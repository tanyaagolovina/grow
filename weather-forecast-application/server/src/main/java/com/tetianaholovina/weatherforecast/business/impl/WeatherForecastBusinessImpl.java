package com.tetianaholovina.weatherforecast.business.impl;

import com.tetianaholovina.weatherforecast.business.WeatherForecastBusiness;
import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.model.forecast.WeatherForecastRequest;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.LocationDetails;
import com.tetianaholovina.weatherforecast.service.forecast.DataProcessService;
import com.tetianaholovina.weatherforecast.service.forecast.WeatherService;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.tetianaholovina.weatherforecast.constants.GenericConstants.CONTENT_TYPE;

@Component
public class WeatherForecastBusinessImpl implements WeatherForecastBusiness {

    private WeatherService weatherService;

    private DataProcessService dataProcessService;

    public WeatherForecastBusinessImpl(WeatherService weatherService, DataProcessService dataProcessService) {
        this.weatherService = weatherService;
        this.dataProcessService = dataProcessService;
    }


    @Override
    public WeatherForecastResponseForNDays getWeatherForecastForNDays(String cityName, String countryName, int noOfDays) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException {

        WeatherForecastResponseForNDays response = null;

        WeatherForecastRequest request = new WeatherForecastRequest(cityName, countryName, CONTENT_TYPE);

        Optional<String> weatherApiResponse = Optional.of(weatherService.fetchWeatherForecastParams(request));

        if(!weatherApiResponse.get().isEmpty()) {
           response = dataProcessService.processWeatherDataForNDays(weatherApiResponse.get(), noOfDays);
           response.setLocationDetails(new LocationDetails(cityName, countryName));
           response.setInvocationDateTime(LocalDateTime.now());
        }
        return response;
    }

    @Override
    public CurrentWeatherForecastResponse getCurrentWeatherForecast(String cityName, String countryName) throws DataNotFoundException, UnauthorizedException, WeatherForecastException, IOException{
        CurrentWeatherForecastResponse response = null;

        WeatherForecastRequest request = new WeatherForecastRequest(cityName, countryName, CONTENT_TYPE);

        Optional<String> weatherApiResponse = Optional.of(weatherService.fetchWeatherForecastParams(request));

        if (!weatherApiResponse.get().isEmpty()) {
            response = dataProcessService.processCurrentWeatherData(weatherApiResponse.get());
            response.setLocationDetails(new LocationDetails(cityName, countryName));
            response.setInvocationDateTime(LocalDateTime.now());
        }
        return response;
    }
}
