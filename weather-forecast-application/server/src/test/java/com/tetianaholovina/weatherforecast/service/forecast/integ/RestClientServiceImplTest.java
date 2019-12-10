package com.tetianaholovina.weatherforecast.service.forecast.integ;

import com.tetianaholovina.weatherforecast.WeatherForecastApplication;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.service.forecast.ApiClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WeatherForecastApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class RestClientServiceImplTest {

    @Autowired
    private ApiClientService apiClientService;

    @Value("${application.test.apikey}")
    private String apiKey;

    private String uri = "https://api.openweathermap.org/data/2.5/forecast?q=Kiev,ua&mode=json";

    @Test
    public void invokeGetResource() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        HttpHeaders httpHeaders = getHeaders();
        ResponseEntity<String> response = apiClientService.invokeGetResource(uri, httpHeaders);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("x-api-key", apiKey);
        return httpHeaders;
    }
}
