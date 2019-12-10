package com.tetianaholovina.weatherforecast.service.schedule.impl;


import com.tetianaholovina.weatherforecast.dao.PlanDao;
import com.tetianaholovina.weatherforecast.dao.UserDao;
import com.tetianaholovina.weatherforecast.dao.jpa.PlanRepository;
import com.tetianaholovina.weatherforecast.dao.jpa.UserRepository;
import com.tetianaholovina.weatherforecast.model.domain.Plan;
import com.tetianaholovina.weatherforecast.service.schedule.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private PlanDao planDao;

    private UserDao userDao;


    public PlanServiceImpl(PlanRepository planDao, UserRepository userDao) {
        this.planDao = planDao;
        this.userDao = userDao;
    }

    @Override
    public Plan save(Plan plan) {
        return planDao.save(plan);
    }

    @Override
    public List<Plan> findAllByUserId(String userId) {
        return planDao.findAllByUser(userDao.findById(userId).get());
    }
}
