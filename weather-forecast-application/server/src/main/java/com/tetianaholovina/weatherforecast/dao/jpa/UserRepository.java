package com.tetianaholovina.weatherforecast.dao.jpa;

import com.tetianaholovina.weatherforecast.dao.UserDao;
import com.tetianaholovina.weatherforecast.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends UserDao, CrudRepository<User, String> {

    @Override
    Optional<User> findUserByEmail(String email);

    @Override
    User save(User user);

    @Override
    Optional<User> findById(String id);

    @Override
    Optional<User> getByEmail(String email);
}
