package com.tetianaholovina.weatherforecast.dao;

import com.tetianaholovina.weatherforecast.model.domain.Plan;
import com.tetianaholovina.weatherforecast.model.domain.User;
import java.util.List;

public interface PlanDao {

    List<Plan> findAllByUser(User user);

    Plan save(Plan plan);
}
