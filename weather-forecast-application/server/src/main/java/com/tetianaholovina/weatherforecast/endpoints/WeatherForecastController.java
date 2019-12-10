package com.tetianaholovina.weatherforecast.endpoints;

import com.tetianaholovina.weatherforecast.business.WeatherForecastBusiness;
import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.exception.DataNotFoundException;
import com.tetianaholovina.weatherforecast.exception.UnauthorizedException;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;

@Validated
@RequestMapping("/weather")
@RestController
@CacheConfig(cacheNames = {"cacheWeatherForecast"})
@Api(tags = "Weather forecast API", value = "Fetches the weather forecast for 3 consecutive days.")
public class WeatherForecastController {

    private WeatherForecastBusiness weatherForecastBusiness;

    public WeatherForecastController(WeatherForecastBusiness weatherForecastBusiness) {
        this.weatherForecastBusiness = weatherForecastBusiness;
    }


    @GetMapping("/forecasts")
    @Cacheable(value = "cacheWeatherForecast")
    @ApiOperation(value = "Get the average day and nightly temperature daily for 3 consecutive days.",
            notes = "This API invokes the 3rd party API - https://openweathermap.org to fetch the weather "
                    + "forecast data i.e. temperature in degrees and pressure in hpa parameters.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - tanyaagolovina@gmail.com"),
            @ApiResponse(code = 200, message = "Weather forecast has been successfully fetched.") })
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description"
            + " for more details.")
    public ResponseEntity<WeatherForecastResponseForNDays> fetchWeatherForecast(
    @Valid
    @Size(min = 1,message = "City name should be more than 1 symbol")
    @Pattern(regexp = "[a-zA-Z]*", message = "Only alphabets are allowed in the city name. No numeric and special characters.")
    @RequestParam(value="city") String cityName,
    @Valid
    @Size(min = 1,message = "Country name should be more than 1 symbol")
    @Pattern(regexp = "[a-zA-Z]*", message = "Only alphabets are allowed in the country name. No numeric and special characters.")
    @RequestParam(value = "country") String countryName,
    @Valid
    @NotNull
    @RequestParam(value = "numberOfDays") Integer noOfDays) throws DataNotFoundException, WeatherForecastException, UnauthorizedException, IOException {

        final WeatherForecastResponseForNDays weatherForecastResponseForNDays = weatherForecastBusiness.getWeatherForecastForNDays(cityName, countryName, noOfDays);

        return ResponseEntity.ok().body(weatherForecastResponseForNDays);
    }

    @GetMapping("/forecasts/current")
    @Cacheable(value = "cacheWeatherForecast")
    @ApiOperation(value = "Get the current weather forecast(temperature, pressure, wind speed, description).",
            notes = "This API invokes the 3rd party API - https://openweathermap.org to fetch the weather "
                    + "forecast data i.e. temperature in degrees and pressure in hpa parameters.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - tanyaagolovina@gmail.com"),
            @ApiResponse(code = 200, message = "Weather forecast has been successfully fetched.") })
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description"
            + " for more details.")
    public ResponseEntity<CurrentWeatherForecastResponse> fetchCurrentWeatherForecast(
            @Valid
            @Size(min = 1,message = "City name should be more than 1 symbol")
            @Pattern(regexp = "[a-zA-Z]*", message = "Only alphabets are allowed in the city name. No numeric and special characters.")
            @RequestParam(value="city") String cityName,
            @Valid
            @Size(min = 1,message = "Country name should be more than 1 symbol")
            @Pattern(regexp = "[a-zA-Z]*", message = "Only alphabets are allowed in the country name. No numeric and special characters.")
            @RequestParam(value = "country") String countryName) throws DataNotFoundException, WeatherForecastException, UnauthorizedException, IOException {

        final CurrentWeatherForecastResponse currentWeatherForecastResponse = weatherForecastBusiness.getCurrentWeatherForecast(cityName, countryName);

        return ResponseEntity.ok().body(currentWeatherForecastResponse);
    }
}
