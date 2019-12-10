package com.tetianaholovina.weatherforecast.service.schedule;

import com.tetianaholovina.weatherforecast.model.domain.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(String id);

    Optional<User> getByEmail(String email);
}
