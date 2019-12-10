package com.tetianaholovina.weatherforecast.dao;

import com.tetianaholovina.weatherforecast.model.domain.User;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByEmail(String email);

    User save(User user);

    Optional<User> findById(String id);

    Optional<User> getByEmail(String email);

}
