package com.tetianaholovina.weatherforecast.service.forecast.integ;

import com.tetianaholovina.weatherforecast.WeatherForecastApplication;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.service.forecast.ApiClientService;
import com.tetianaholovina.weatherforecast.service.forecast.DataProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.Collections;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WeatherForecastApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class WeatherDataProcessServiceImplTest {

    @Autowired
    private DataProcessService weatherDataProcessService;

    @Autowired
    private ApiClientService apiClientService;

    @Value("${application.test.apikey}")
    private String apiKey;

    @Value("${application.test.noOfDays}")
    private int noOfDays;

    private String uri = "https://api.openweathermap.org/data/2.5/forecast?q=Kiev,ua&mode=json";


    @Test
    public void processWeatherDataForNDaysWithValidData() throws WeatherForecastException, DataNotFoundException, UnauthorizedException, IOException {
        //given
        ResponseEntity<String> jsonData = apiClientService.invokeGetResource(uri, getHeaders());
        //when
        WeatherForecastResponseForNDays responseForNDays = weatherDataProcessService.processWeatherDataForNDays(jsonData.getBody(), noOfDays);
        //then
        assertNotNull(responseForNDays);
        assertNotNull(responseForNDays.getAverageWeatherForecastForNDays());
        assertNotNull(responseForNDays.getAverageWeatherForecastForNDays().get(0).getAvgDailyTemperature());
        assertNotNull(responseForNDays.getAverageWeatherForecastForNDays().get(0).getAvgNightTemperature());
        assertNotNull(responseForNDays.getAverageWeatherForecastForNDays().get(0).getAvgPressure());
        assertNotNull(responseForNDays.getAverageWeatherForecastForNDays().get(0).getDate());
        assertNotEquals(responseForNDays.getAverageWeatherForecastForNDays().get(0).getDate(), responseForNDays.getAverageWeatherForecastForNDays().get(1).getDate());
    }

    @Test(expected = WeatherForecastException.class)
    public void processWeatherDataForNDaysWithNullPassedValue() throws WeatherForecastException, DataNotFoundException, UnauthorizedException, IOException {
        weatherDataProcessService.processWeatherDataForNDays(null, noOfDays);
    }

    @Test(expected = WeatherForecastException.class)
    public void processWeatherDataForNDaysWithBlankData() throws WeatherForecastException, DataNotFoundException, UnauthorizedException, IOException {
        weatherDataProcessService.processWeatherDataForNDays("", noOfDays);
    }

    @Test
    public void processCurrentWeatherData() {
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("x-api-key", apiKey);
        return httpHeaders;
    }
}
