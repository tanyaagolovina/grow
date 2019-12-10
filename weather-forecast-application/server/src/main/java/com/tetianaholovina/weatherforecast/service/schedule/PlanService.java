package com.tetianaholovina.weatherforecast.service.schedule;

import com.tetianaholovina.weatherforecast.model.domain.Plan;

import java.util.List;

public interface PlanService {
    Plan save(Plan plan);

    List<Plan> findAllByUserId(String userId);
}
