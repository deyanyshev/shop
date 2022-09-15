package com.internship.site.repository;

import com.internship.site.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findAll();
    List<User> findAllByOrderByIdAsc();
    User findById(int id);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
}
