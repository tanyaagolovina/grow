package com.tetianaholovina.weatherforecast.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import static com.tetianaholovina.weatherforecast.utils.WeatherForecastUtils.getRequiredDatesFromRange;

public final class DateRangePredicate {

    private DateRangePredicate(){}

    public static Predicate<LocalDateTime> isWithinDayRange(final double noOfDays){

        Predicate<LocalDateTime> dateRangePredicate = localDateTime -> {

            List<LocalDate> dates = getRequiredDatesFromRange(noOfDays);

            for (LocalDate rangeDate : dates) {
                LocalDateTime dateTimeFrom = LocalDateTime.of(rangeDate.getYear(), rangeDate.getMonth(),
                        rangeDate.getDayOfMonth(), 06, 00);
                LocalDateTime dateTimeTo = LocalDateTime.of(dateTimeFrom.getYear(), dateTimeFrom.getMonth(),
                        dateTimeFrom.getDayOfMonth(), 15, 00);

                if (isInRange(localDateTime, dateTimeFrom, dateTimeTo)) {
                    return true;
                }
            }
            return false;
        };
        return dateRangePredicate;
    }

    public static Predicate<LocalDateTime> isWithinNightRange(final double noOfDays){
        Predicate<LocalDateTime> dateRangePredicate = localDateTime -> {
            List<LocalDate> dates = getRequiredDatesFromRange(noOfDays);

            for (LocalDate rangeDate : dates) {
                LocalDateTime dateTimeFrom = LocalDateTime.of(rangeDate.getYear(), rangeDate.getMonth(),
                        rangeDate.getDayOfMonth(), 18, 00);
                LocalDateTime nextDate = dateTimeFrom.plusDays(1);
                LocalDateTime dateTimeTo = LocalDateTime.of(nextDate.getYear(), nextDate.getMonth(),
                        nextDate.getDayOfMonth(), 06, 00);

                if (isInRange(localDateTime, dateTimeFrom, dateTimeTo)) {
                    return true;
                }
            }
            return false;
        };
        return dateRangePredicate;
    }

    private static boolean isInRange(LocalDateTime localDateTime, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        return (localDateTime.isAfter(dateTimeFrom) || localDateTime.isEqual(dateTimeFrom))
                            && (localDateTime.isBefore(dateTimeTo) || localDateTime.isEqual(dateTimeTo));
    }
}
