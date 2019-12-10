package com.tetianaholovina.weatherforecast.utils;

import org.junit.Test;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateRangePredicateTest {

    @Test
    public void isWithinDayRangeReturnsTrue() {
        //given
        Predicate<LocalDateTime> dateRangePredicate = DateRangePredicate.isWithinDayRange(3);
        //when
        boolean isWithinDay = dateRangePredicate.test(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 12, 0));
        //then
        assertTrue(isWithinDay);
    }

    @Test
    public void isWithinDayRangeReturnsFalse() {
        //given
        Predicate<LocalDateTime> dateRangePredicate = DateRangePredicate.isWithinDayRange(3);
        //when
        boolean isWithinDay = dateRangePredicate.test(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 19, 0));
        //then
        assertFalse(isWithinDay);
    }

    @Test
    public void isWithinNightRangeReturnsTrue() {
        //given
        Predicate<LocalDateTime> dateRangePredicate = DateRangePredicate.isWithinNightRange(3);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 03, 0);
        //when
        boolean isWithinNight = dateRangePredicate.test(localDateTime.plusDays(1));
        //then
        assertTrue(isWithinNight);
    }

    @Test
    public void isWithinNightRangeReturnsFalse() {
        //given
        Predicate<LocalDateTime> dateRangePredicate = DateRangePredicate.isWithinNightRange(3);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 7, 0);
        //when
        boolean isWithinNight = dateRangePredicate.test(localDateTime.plusDays(1));
        //then
        assertFalse(isWithinNight);
    }
}
