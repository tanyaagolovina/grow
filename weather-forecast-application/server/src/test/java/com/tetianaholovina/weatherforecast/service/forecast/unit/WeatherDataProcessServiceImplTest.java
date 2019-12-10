package com.tetianaholovina.weatherforecast.service.forecast.unit;

import com.fasterxml.jackson.databind.JsonNode;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.service.forecast.ParserService;
import com.tetianaholovina.weatherforecast.service.forecast.impl.WeatherDataProcessServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static com.tetianaholovina.weatherforecast.utilsForTests.TestUtils.getJsonNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataProcessServiceImplTest {
    @Mock
    ParserService parser;

    @InjectMocks
    WeatherDataProcessServiceImpl weatherDataProcessService;

    private String jsonResp;

    private JsonNode jsonNode = null;

    @Before
    public void setUp() throws IOException {
        ClassPathResource resource = new ClassPathResource("src/test/resources/testData/oldForecastData.json");
        File file = new File(resource.getPath());
        jsonResp = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        jsonNode = getJsonNode(jsonResp);
    }

    @Test (expected = WeatherForecastException.class)
    public void processWeatherDataForNDaysWithInvalidJsonData() throws IOException, WeatherForecastException {
        jsonResp = "";
        weatherDataProcessService.processWeatherDataForNDays(jsonResp, 3);
    }

    @Test (expected = WeatherForecastException.class)
    public void processCurrentWeatherDataWithInvalidJsonData() throws IOException, WeatherForecastException {
        jsonResp = "";
        weatherDataProcessService.processCurrentWeatherData(jsonResp);
    }

    @Test
    public void processCurrentWeatherDataWithValidJsonData() throws IOException, WeatherForecastException {
        when(parser.parseData(Mockito.anyString())).thenReturn(jsonNode);

        CurrentWeatherForecastResponse weatherForecastResponse = weatherDataProcessService.processCurrentWeatherData(jsonResp);

        assertNotNull(weatherForecastResponse);
        /*assertEquals("272.64", weatherForecastResponse.getCurrentWeather().getTemperature().getTemperature());*/
    }

    @Test
    public void processWeatherDataForNDaysReturnsZeroPressureWhenPastDataReceived() throws IOException, WeatherForecastException {
        when(parser.parseData(Mockito.anyString())).thenReturn(jsonNode);

        WeatherForecastResponseForNDays weatherForecastResponse = weatherDataProcessService.processWeatherDataForNDays(jsonResp, 3);

        assertEquals("NA", weatherForecastResponse.getAverageWeatherForecastForNDays().get(0).getAvgPressure());
    }

    @Test
    public void processWeatherDataForNDaysReturnsZeroTemperatureWhenPastDataReceived() throws IOException, WeatherForecastException {
        when(parser.parseData(Mockito.anyString())).thenReturn(jsonNode);

        WeatherForecastResponseForNDays weatherForecastResponse = weatherDataProcessService.processWeatherDataForNDays(jsonResp, 3);

        assertEquals("NA", weatherForecastResponse.getAverageWeatherForecastForNDays().get(0).getAvgDailyTemperature().getTemperature());
        assertEquals("NA", weatherForecastResponse.getAverageWeatherForecastForNDays().get(0).getAvgNightTemperature().getTemperature());
    }
}
