package com.tetianaholovina.weatherforecast.service.forecast.impl;

import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.service.forecast.ApiClientService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.PARAM;

/**
 * <p>This class has a responsibility of invoking the third party rest API to fetch the weather
 * forecast metrics</p>
 */
@Service
public class RestClientServiceImpl implements ApiClientService {

    private RestTemplate restTemplate;

    public RestClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> invokeGetResource(String uri, HttpHeaders httpHeaders) throws UnauthorizedException, DataNotFoundException, WeatherForecastException {
        try {
            HttpEntity<String> entity = new HttpEntity<>(PARAM, httpHeaders);
            return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException exception) {

            HttpStatus httpStatus = exception.getStatusCode();

            if(HttpStatus.UNAUTHORIZED.equals(httpStatus)) {
                throw new UnauthorizedException("Unauthorized exception");
            } else if (HttpStatus.NOT_FOUND.equals(httpStatus)){
                throw new DataNotFoundException("We could not find the data of the city you are looking for");
            } else {
                throw new WeatherForecastException("Something went wrong with invoking the weather API");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WeatherForecastException("Something went wrong with invoking the weather API", ex);
        }
    }
}
