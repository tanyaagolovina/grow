package com.tetianaholovina.weatherforecast.service.forecast.unit;

import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.service.forecast.impl.RestClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestClientServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private HttpClientErrorException httpClientErrorException;

    @Mock
    private ResponseEntity<String> mockedRespEntity;

    @InjectMocks
    private RestClientServiceImpl restClientService;

    @Test
    public void invokeGetResourceReturns200OkAndSuccessJsonResponse() throws WeatherForecastException, DataNotFoundException, UnauthorizedException {
        when(mockedRespEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenReturn(mockedRespEntity);

        HttpHeaders httpHeaders = getHeaders();
        ResponseEntity<String> response = restClientService.invokeGetResource("http://api.openweathermap.org/data/2.5/forecast?q=Kyiv,ua&mode=json", httpHeaders);

        assertNotNull(response);
        assertSame(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = UnauthorizedException.class)
    public void invokeGetResourceThrowsUnauthorizedException() throws UnauthorizedException, WeatherForecastException, DataNotFoundException {
        when(httpClientErrorException.getStatusCode()).thenReturn(HttpStatus.UNAUTHORIZED);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(httpClientErrorException);

        HttpHeaders httpHeaders = getHeaders();
        restClientService.invokeGetResource("http://api.openweathermap.org/data/2.5/forecast?q=Kyiv,ua&mode=json", httpHeaders);
    }

    @Test(expected = DataNotFoundException.class)
    public void invokeGetResourceThrowsDataNotFoundException() throws UnauthorizedException, WeatherForecastException, DataNotFoundException {
        when(httpClientErrorException.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(httpClientErrorException);

        HttpHeaders httpHeaders = getHeaders();
        restClientService.invokeGetResource("http://api.openweathermap.org/data/2.5/forecast?q=Kyiv,ua&mode=json", httpHeaders);
    }

    @Test(expected = WeatherForecastException.class)
    public void invokeGetResourceThrowsWeatherForecastException() throws UnauthorizedException, WeatherForecastException, DataNotFoundException {
        when(httpClientErrorException.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()
        )).thenThrow(httpClientErrorException);

        HttpHeaders httpHeaders = getHeaders();
        restClientService.invokeGetResource("http://api.openweathermap.org/data/2.5/forecast?q=Kyiv,ua&mode=json", httpHeaders);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("x-api-key","5ebcf5eae2f0fe2924bf6ea7d3730819");
        return httpHeaders;
    }
}
