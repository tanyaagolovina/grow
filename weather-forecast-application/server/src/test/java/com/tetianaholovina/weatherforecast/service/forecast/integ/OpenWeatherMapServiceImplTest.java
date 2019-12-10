package com.tetianaholovina.weatherforecast.service.forecast.integ;

import com.fasterxml.jackson.databind.JsonNode;
import com.tetianaholovina.weatherforecast.WeatherForecastApplication;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.WeatherForecastRequest;
import com.tetianaholovina.weatherforecast.service.forecast.ParserService;
import com.tetianaholovina.weatherforecast.service.forecast.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WeatherForecastApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class OpenWeatherMapServiceImplTest {

    @Autowired
    WeatherService weatherService;

    @Autowired
    ParserService parserService;

    @Test
    public void fetchWeatherForecastParamsVerifiesNotNullResponse() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        //given
        WeatherForecastRequest request = new WeatherForecastRequest("Kyiv", "ua", "json");
        //when
        String response = weatherService.fetchWeatherForecastParams(request);
        //then
        assertNotNull(response);
    }

    @Test
    public void fetchWeatherForecastParamsVerifiesParsableData() throws WeatherForecastException, DataNotFoundException, UnauthorizedException, IOException {
        //given
        WeatherForecastRequest request = new WeatherForecastRequest("Kyiv", "ua", "json");
        //when
        String response = weatherService.fetchWeatherForecastParams(request);
        JsonNode jsonNode = parserService.parseData(response);
        //then
        assertNotNull(jsonNode.path("dt_txt").asText());
        assertNotNull(jsonNode.path("list").path("main").path("temp").asText());
        assertNotNull(jsonNode.path("list").path("main").path("pressure").asText());
    }
}
