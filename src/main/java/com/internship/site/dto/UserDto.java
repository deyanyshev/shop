package com.internship.site.dto;

import com.internship.site.entity.Product;
import com.internship.site.entity.enums.Role;
import lombok.Data;

import java.util.List;

/**
 * DTO для отображения, создания и аутентификации пользователей
 *
 * @author deyanyshev
 */

@Data
public class UserDto {
    int id;

    /**
     * Имя пользователя
     */
    String name;
    /**
     * Логин пользователя
     */
    String login;
    /**
     * Пароль пользователя
     */
    String password;
    /**
     * Почта пользователя
     */
    String email;
    /**
     * Роль пользователя
     */
    Role role;
}
