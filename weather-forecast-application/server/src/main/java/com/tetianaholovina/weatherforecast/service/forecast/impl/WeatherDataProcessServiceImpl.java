package com.tetianaholovina.weatherforecast.service.forecast.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.tetianaholovina.weatherforecast.constants.GenericConstants;
import com.tetianaholovina.weatherforecast.dto.PressureParameters;
import com.tetianaholovina.weatherforecast.dto.TemperatureParameters;
import com.tetianaholovina.weatherforecast.exception.WeatherForecastException;
import com.tetianaholovina.weatherforecast.model.forecast.AverageWeatherForecast;
import com.tetianaholovina.weatherforecast.model.forecast.ForecastDetails;
import com.tetianaholovina.weatherforecast.model.forecast.Temperature;
import com.tetianaholovina.weatherforecast.model.forecast.WeatherData;
import com.tetianaholovina.weatherforecast.model.forecast.responses.CurrentWeatherForecastResponse;
import com.tetianaholovina.weatherforecast.model.forecast.responses.WeatherForecastResponseForNDays;
import com.tetianaholovina.weatherforecast.service.forecast.DataProcessService;
import com.tetianaholovina.weatherforecast.service.forecast.ParserService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.ALL;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.BASE_VAL;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.CLOUDS;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.DATE_TXT_FIELD;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.DAY_TM;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.DESCRIPTION;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.MAIN;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.NIGHT_TM;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.NOT_AVAILABL;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.PRESSURE;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.SPEED;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.TEMP;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.WEATHER;
import static com.tetianaholovina.weatherforecast.constants.GenericConstants.WIND;
import static com.tetianaholovina.weatherforecast.utils.DateRangePredicate.isWithinDayRange;
import static com.tetianaholovina.weatherforecast.utils.DateRangePredicate.isWithinNightRange;
import static com.tetianaholovina.weatherforecast.utils.WeatherForecastUtils.calcAvgPressure;
import static com.tetianaholovina.weatherforecast.utils.WeatherForecastUtils.calcAvgTemp;
import static com.tetianaholovina.weatherforecast.utils.WeatherForecastUtils.getCurrentTime;
import static com.tetianaholovina.weatherforecast.utils.WeatherForecastUtils.getRequiredDatesFromRange;

/**
 * <p>
 * This class has a responsibility of processing the json message which is
 * fetched by invoking the weather API. It filters out the data as per the date
 * range from the 3 days data which is present. It divides the data according
 * to the day and night timing range and then calculates the averages of the
 * weather parameters like temperature and pressure and sends back to the
 * business class {@code WeatherForecastBusinessImpl}.
 * </p>
 */
@Service
public class WeatherDataProcessServiceImpl implements DataProcessService {

    private ParserService parser;

    public WeatherDataProcessServiceImpl(ParserService parser) {
        this.parser = parser;
    }

    @Override
    public WeatherForecastResponseForNDays processWeatherDataForNDays(String jsonData, int noOfDays) throws WeatherForecastException, IOException {

        if(isNotEmptyJson(jsonData)){

            List<TemperatureParameters> daysTemperatureList = new ArrayList<>();
            List<TemperatureParameters> nightTemperatureList = new ArrayList<>();
            List<PressureParameters> pressureList = new ArrayList<>();


            Iterator<JsonNode> jsonNodes = getJsonNodeIterator(jsonData);

            while (jsonNodes.hasNext()) {

                JsonNode node = jsonNodes.next();
                LocalDateTime dateTime = getDateTimeFromJsonNode(node);

                if(isWithinDayRange(noOfDays).test(dateTime)) {
                    daysTemperatureList.add(fetchTemperature(node));
                    pressureList.add(fetchPressure(node));
                }

                if (isWithinNightRange(noOfDays).test(dateTime)){
                    nightTemperatureList.add(fetchTemperature(node));
                    pressureList.add(fetchPressure(node));
                }
            }
            return iterateForDatesAndPopResponse(noOfDays, daysTemperatureList, nightTemperatureList, pressureList);
        } else {
            throw new WeatherForecastException("Input json message is null");
        }
    }

    @Override
    public CurrentWeatherForecastResponse processCurrentWeatherData(String jsonData) throws WeatherForecastException, IOException {

        CurrentWeatherForecastResponse currentWeatherForecastResponse = new CurrentWeatherForecastResponse();

        if(isNotEmptyJson(jsonData)){
            Iterator<JsonNode> jsonNodes = getJsonNodeIterator(jsonData);
            while (jsonNodes.hasNext()){
                JsonNode node = jsonNodes.next();
                currentWeatherForecastResponse.setCurrentWeather(getCurrentWeatherDataFromNode(node));
            }
        }
        else {
            throw new WeatherForecastException("Input json message is null");
        }
        return currentWeatherForecastResponse;
    }

    private WeatherData getCurrentWeatherDataFromNode(JsonNode node) {
        WeatherData weatherData = new WeatherData();
        double temperatureFromNode = fetchTemperature(node).getTemperature();
        Temperature temperature = new Temperature(String.valueOf(temperatureFromNode), getCurrentTime());
        weatherData.setForecastDetails(fetchForecastDetails(node));
        weatherData.setTemperature(temperature);
        weatherData.setDate(getDateTimeFromJsonNode(node).toLocalDate());
        return weatherData;
    }

    private boolean isNotEmptyJson(String jsonData){
        Optional<String> json = Optional.ofNullable(jsonData);
        return json.isPresent() && !json.get().isEmpty();
    }

    private Iterator<JsonNode> getJsonNodeIterator(final String jsonData) throws IOException {
        return parser.parseData(jsonData).iterator();
    }

    private LocalDateTime getDateTimeFromJsonNode(JsonNode node){
        String dateTime = node.path(DATE_TXT_FIELD).asText();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(GenericConstants.DT_TM_FRMT);
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    private PressureParameters fetchPressure(JsonNode node){
        PressureParameters pressureParameters = null;
        if(isNotEmptyDateTxtField(node)){
            pressureParameters = new PressureParameters(getDateTimeFromJsonNode(node).toLocalDate(),
                    node.path(MAIN).path(PRESSURE).asDouble());
        }
        return pressureParameters;
    }

    private TemperatureParameters fetchTemperature(JsonNode node){
        TemperatureParameters temperatureParameters = null;
        if(isNotEmptyDateTxtField(node)){
            temperatureParameters = new TemperatureParameters(getDateTimeFromJsonNode(node).toLocalDate(),
                    node.path(MAIN).path(TEMP).asDouble());
        }
        return temperatureParameters;
    }

    private ForecastDetails fetchForecastDetails(JsonNode node){
        String description = node.path(WEATHER).findValue(DESCRIPTION).asText();
        double windSpeed = node.path(WIND).path(SPEED).asDouble();
        double clouds = node.path(CLOUDS).path(ALL).asDouble();
        return new ForecastDetails(description, windSpeed, clouds);
    }


    /**
     * This method gets the 3 elligible dates starting from the current date for which the
     * temperature stats are to be returned back. It loops for each date , calculates the
     * weather metrics i.e. day,night average temperature and pressure and sends back
     * {@code WeatherForecastResp}
     *
     * @param  dayTemperatureList        The average day temperature list.
     * @param  nightTemperatureList    The average nightly temperature list.
     * @param  pressureList    The average pressure list.
     * @return WeatherForecastResponseForNDays The api response object.
     */

    private WeatherForecastResponseForNDays iterateForDatesAndPopResponse(double noOfDays, List<TemperatureParameters> dayTemperatureList,
                                                                          List<TemperatureParameters> nightTemperatureList,
                                                                          List<PressureParameters> pressureList) {

        List<LocalDate> dates = getRequiredDatesFromRange(noOfDays);
        WeatherForecastResponseForNDays weatherForecastResponseForNDays = new WeatherForecastResponseForNDays();

        for (LocalDate localDate:dates) {
            double dayTemperatureAvgValue = calcAvgTemp(dayTemperatureList, localDate, BASE_VAL);
            double nightTemperatureAvgValue = calcAvgTemp(nightTemperatureList, localDate, BASE_VAL);
            double pressureAvgValue = calcAvgPressure(pressureList, localDate, BASE_VAL);
            AverageWeatherForecast averageWeatherForecast = populateResponse(dayTemperatureAvgValue, nightTemperatureAvgValue, pressureAvgValue, localDate);
            weatherForecastResponseForNDays.getAverageWeatherForecastForNDays().add(averageWeatherForecast);
        }

        return weatherForecastResponseForNDays;
    }

    private AverageWeatherForecast populateResponse(double dayTemperatureAvgValue, double nightTemperatureAvgValue, double pressureAvgValue, LocalDate date){
        return  new AverageWeatherForecast(date, getDailyTemperature(dayTemperatureAvgValue),
                getNightTemperature(nightTemperatureAvgValue), getPressure(pressureAvgValue));
    }

    private Temperature getDailyTemperature(double dayTemperature) {
        Temperature temperature;
        if(dayTemperature == BASE_VAL){
            temperature = new Temperature(NOT_AVAILABL, DAY_TM);
        } else {
            temperature = new Temperature(Double.toString(dayTemperature), DAY_TM);
        }
        return temperature;
    }

    private Temperature getNightTemperature(double nightTemperature) {
        Temperature temperature;
        if(nightTemperature == BASE_VAL){
            temperature = new Temperature(NOT_AVAILABL, NIGHT_TM);
        } else {
            temperature = new Temperature(Double.toString(nightTemperature), NIGHT_TM);
        }
        return temperature;
    }


    private String getPressure(double pressure) {
       String pressureValue;
        if(pressure == BASE_VAL){
            pressureValue = NOT_AVAILABL;
        } else {
            pressureValue = Double.toString(pressure);
        }
        return pressureValue;
    }

    private boolean isNotEmptyDateTxtField(JsonNode node){
        return !node.path(DATE_TXT_FIELD).asText().isEmpty();
    }
}
