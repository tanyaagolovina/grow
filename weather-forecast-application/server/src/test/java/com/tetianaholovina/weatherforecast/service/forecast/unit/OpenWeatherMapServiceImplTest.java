package com.tetianaholovina.weatherforecast.service.forecast.unit;

import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.WeatherForecastRequest;
import com.tetianaholovina.weatherforecast.service.forecast.ApiClientService;
import com.tetianaholovina.weatherforecast.service.forecast.impl.OpenWeatherMapServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapServiceImplTest {

    @Mock
    ApiClientService apiClient;

    @Mock
    ResponseEntity<String> responseEntity;

    @InjectMocks
    OpenWeatherMapServiceImpl openWeatherMapService;

    private String jsonResp;

    @Before
    public void setUp() throws IOException {
        ClassPathResource resource = new ClassPathResource("src/test/resources/testData/successJsonResponse.json");
        File file = new File(resource.getPath());
        jsonResp = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    }

    @Test
    public void fetchWeatherForecastParamsReturnsSuccessResponse() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        when(apiClient.invokeGetResource(Mockito.anyString(), Mockito.any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(jsonResp);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        WeatherForecastRequest weatherForecastRequest = new WeatherForecastRequest("London", "us", "json");
        String response = openWeatherMapService.fetchWeatherForecastParams(weatherForecastRequest);

        assertNotNull(response);
        assertEquals(response, jsonResp);
    }

    @Test(expected = DataNotFoundException.class)
    public void fetchWeatherForecastParamsThrowsDataNotFoundExceptionNullResponse() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        when(apiClient.invokeGetResource(Mockito.anyString(), Mockito.any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(null);

        WeatherForecastRequest weatherForecastRequest = new WeatherForecastRequest("London", "us", "json");
        openWeatherMapService.fetchWeatherForecastParams(weatherForecastRequest);
    }

    @Test(expected = DataNotFoundException.class)
    public void fetchWeatherForecastParamsThrowsDataNotFoundExceptionBlankResponse() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        when(apiClient.invokeGetResource(Mockito.anyString(), Mockito.any())).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn("");

        WeatherForecastRequest weatherForecastRequest = new WeatherForecastRequest("London", "us", "json");
        openWeatherMapService.fetchWeatherForecastParams(weatherForecastRequest);
    }
}
