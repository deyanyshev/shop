package com.internship.site.repository;

import com.internship.site.entity.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findAll();
    List<User> findAllByOrderByIdAsc();
    User findById(int id);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    @Modifying
    @Query(value = "INSERT INTO purchases (product_id, user_id) VALUES (:p_id,:u_id)", nativeQuery = true)
    @Transactional
    void setProduct(@Param("p_id") int product_id, @Param("u_id") int user_id);

    @Modifying
    @Query(value = "DELETE FROM purchases WHERE product_id = :p_id AND user_id = :u_id", nativeQuery = true)
    @Transactional
    void deleteProduct(@Param("p_id") int product_id, @Param("u_id") int user_id);
}
