package com.tetianaholovina.weatherforecast.dao.jpa;

import com.tetianaholovina.weatherforecast.dao.PlanDao;
import com.tetianaholovina.weatherforecast.model.domain.Plan;
import com.tetianaholovina.weatherforecast.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PlanRepository extends PlanDao, CrudRepository<Plan, Integer> {

    @Override
    List<Plan> findAllByUser(User user);

    @Override
    Plan save(Plan plan);
}
