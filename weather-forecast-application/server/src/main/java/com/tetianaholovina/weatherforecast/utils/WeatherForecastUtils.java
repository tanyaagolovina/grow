package com.tetianaholovina.weatherforecast.utils;

import com.tetianaholovina.weatherforecast.dto.PressureParameters;
import com.tetianaholovina.weatherforecast.dto.TemperatureParameters;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class WeatherForecastUtils {

    private WeatherForecastUtils() {}

    public static LocalDate getTodaysDate(){
        return LocalDate.now();
    }

    public static String getCurrentTime(){
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static List<LocalDate> getRequiredDatesFromRange(double numberOfDays){
        long noOfDays = (long) numberOfDays;
        return IntStream.iterate(0, i -> i+1)
                .limit(noOfDays)
                .mapToObj(i -> LocalDate.now().plusDays(i))
                .collect(Collectors.toList());
    }


    public static double calcAvgPressure(List<PressureParameters> pressuremetrcsDtlList, LocalDate lclDt, double baseValue) {
        return pressuremetrcsDtlList.stream()
                .filter(i->i.getLocalDate().isEqual(lclDt))
                .collect(Collectors.toList())
                .stream().map(PressureParameters::getPressure)
                .mapToDouble(g->g).average().orElse(baseValue);
    }

    public static double calcAvgTemp(List<TemperatureParameters> tempListDay, LocalDate lclDt, double baseValue) {
        return tempListDay.stream()
                .filter(i->i.getLocalDate().isEqual(lclDt))
                .collect(Collectors.toList())
                .stream().map(TemperatureParameters::getTemperature)
                .mapToDouble(g->g)
                .average().orElse(baseValue);
    }
}
