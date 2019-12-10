package com.tetianaholovina.weatherforecast.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateComparator implements Comparator<Plan> {
    @Override
    public int compare(Plan plan1, Plan plan2) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plan1DtTm = LocalDateTime.parse(plan1.getEvent().getDatetime(), dateTimeFormatter);
        LocalDateTime plan2DtTm = LocalDateTime.parse(plan2.getEvent().getDatetime(), dateTimeFormatter);
        if(plan1DtTm.isBefore(plan2DtTm)){
            return -1;
        } else if(plan1DtTm.isAfter(plan2DtTm)){
            return 1;
        } else return 0;
    }
}
