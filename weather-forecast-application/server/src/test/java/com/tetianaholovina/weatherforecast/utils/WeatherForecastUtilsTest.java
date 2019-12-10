package com.tetianaholovina.weatherforecast.utils;

import com.tetianaholovina.weatherforecast.dto.PressureParameters;
import com.tetianaholovina.weatherforecast.dto.TemperatureParameters;
import org.junit.BeforeClass;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeatherForecastUtilsTest {
    private static double baseValue;

    private static LocalDate lclDt;

    @BeforeClass
    public static void setUp() {
        baseValue = 0;
        lclDt = LocalDate.now();
    }

    @Test
    public void getRequiredDatesFromRange() {
        List<LocalDate> dates = WeatherForecastUtils.getRequiredDatesFromRange(3);

        assertNotNull(dates);
        assertEquals(LocalDate.now(), dates.get(0));
        assertEquals(3, dates.size());
    }

    @Test
    public void calcAvgPressureWithValidParameters() {
        //given
        List<PressureParameters> tempListDay = Arrays.asList(
                new PressureParameters(LocalDate.now(), 30.00),
                new PressureParameters(LocalDate.now(), 60.00)
                );
        //when
        double avgPressure = WeatherForecastUtils.calcAvgPressure(tempListDay, lclDt, baseValue);
        //then
        assertEquals(45.00, avgPressure, 00.00);
    }

    @Test
    public void calcAvgTempWithValidParameters() {
        //given
        List<TemperatureParameters> tempListDay = Arrays.asList(
                new TemperatureParameters(LocalDate.now(), 45.00),
                new TemperatureParameters(LocalDate.now(), 55.00)
        );
        //when
        double avgTemp = WeatherForecastUtils.calcAvgTemp(tempListDay, lclDt, baseValue);
        //then
        assertEquals(50.00, avgTemp, 00.00);
    }
}
