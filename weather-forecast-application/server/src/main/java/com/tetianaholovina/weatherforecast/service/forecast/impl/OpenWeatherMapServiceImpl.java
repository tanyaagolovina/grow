package com.tetianaholovina.weatherforecast.service.forecast.impl;

import com.tetianaholovina.weatherforecast.constants.GenericConstants;
import com.tetianaholovina.weatherforecast.model.forecast.WeatherForecastRequest;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.service.forecast.ApiClientService;
import com.tetianaholovina.weatherforecast.service.forecast.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collections;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.API_KEY;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.FORECAST_TYPE_CURRENT;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.FORECAST_TYPE_DAY;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.FORECAST_TYPE_HOURLY;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.QUESTION;

/**
 * <p>This class has a responsibility of invoking the weather API to get the weather metrics to get the </p>
 * weather data in json format , converts to java object and then sends it back to the rest endpoints
 * {@code WeatherForecastController}
 */
@Service
public class OpenWeatherMapServiceImpl implements WeatherService {
    @Value("${application.prod.apikey}")
    private String apiKey;

    @Value("${application.prod.weatherForecastURL}")
    private String weatherForecastURL;

    private ApiClientService apiClient;

    public OpenWeatherMapServiceImpl(ApiClientService apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String fetchWeatherForecastParams(WeatherForecastRequest weatherForecastRequest) throws DataNotFoundException, WeatherForecastException, UnauthorizedException {
        ResponseEntity<String> weatherForecastDataResp = (apiClient.invokeGetResource(buildUri(weatherForecastRequest), getHttpHeaders()));
        return getJsonResponseOrThrowException(weatherForecastDataResp);
    }

    /*@Override
    public String fetchCurrentWeatherForecastParams(WeatherForecastRequest weatherForecastRequest) throws DataNotFoundException, WeatherForecastException, UnauthorizedException {
        ResponseEntity<String> currentForecastResp =(apiClient.invokeGetResource(buildUriForCurrentForecast(weatherForecastRequest), getHttpHeaders()));
        return getJsonResponseOrThrowException(currentForecastResp);
    }
*/

    private String getJsonResponseOrThrowException(ResponseEntity<String> weatherForecastDataResp) throws DataNotFoundException {
        if(isResponseValid(weatherForecastDataResp)) {
            return weatherForecastDataResp.getBody();
        } else {
            throw new DataNotFoundException("API service returned blank response");
        }
    }

    private boolean isResponseValid(ResponseEntity<String> weatherForecastDataResp) {
        return weatherForecastDataResp.getBody() != null && !weatherForecastDataResp.getBody().isEmpty()
        && HttpStatus.OK.equals(weatherForecastDataResp.getStatusCode());
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(API_KEY, apiKey);
        return headers;
    }

    private String buildUri(WeatherForecastRequest weatherForecastRequest){
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(weatherForecastURL)
                .append(FORECAST_TYPE_DAY)
                .append(QUESTION)
                .append(GenericConstants.PARAM_VAR)
                .append(weatherForecastRequest.getCityName())
                .append(GenericConstants.COMMA_SEP)
                .append(weatherForecastRequest.getCountryName())
                .append(GenericConstants.MODE)
                .append(weatherForecastRequest.getResponseContentType())
                .append(GenericConstants.UNITS);
        return uriBuilder.toString();
    }

    private String buildUriForHourlyForecast(WeatherForecastRequest weatherForecastRequest){
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(weatherForecastURL)
                .append(FORECAST_TYPE_DAY)
                .append(FORECAST_TYPE_HOURLY)
                .append(QUESTION)
                .append(GenericConstants.PARAM_VAR)
                .append(weatherForecastRequest.getCityName())
                .append(GenericConstants.COMMA_SEP)
                .append(weatherForecastRequest.getCountryName())
                .append(GenericConstants.MODE)
                .append(weatherForecastRequest.getResponseContentType())
                .append(GenericConstants.UNITS);
        return uriBuilder.toString();
    }

    private String buildUriForCurrentForecast(WeatherForecastRequest weatherForecastRequest){
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(weatherForecastURL)
                .append(FORECAST_TYPE_CURRENT)
                .append(QUESTION)
                .append(GenericConstants.PARAM_VAR)
                .append(weatherForecastRequest.getCityName())
                .append(GenericConstants.COMMA_SEP)
                .append(weatherForecastRequest.getCountryName())
                .append(GenericConstants.MODE)
                .append(weatherForecastRequest.getResponseContentType())
                .append(GenericConstants.UNITS);
        return uriBuilder.toString();
    }
}
