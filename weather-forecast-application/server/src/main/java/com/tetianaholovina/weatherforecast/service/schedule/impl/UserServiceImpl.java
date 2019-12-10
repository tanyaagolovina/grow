package com.tetianaholovina.weatherforecast.service.schedule.impl;

import com.tetianaholovina.weatherforecast.dao.UserDao;
import com.tetianaholovina.weatherforecast.dao.jpa.UserRepository;
import com.tetianaholovina.weatherforecast.model.domain.User;
import com.tetianaholovina.weatherforecast.service.schedule.UserService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
