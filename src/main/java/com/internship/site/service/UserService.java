package com.internship.site.service;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    /**
     * Получить всех пользователей из базы данных
     * Возможно только для супер администратора
     * @return Список DTO всех пользователей
     */
    public List<UserDto> getUsers();

    /**
     * Получить пользователя по jwt токену
     * @return DTO текущего пользователя
     */
    public UserDto getUser();

    /**
     * Совершить аутентификацию пользователя
     * @param userDto DTO пользователя с данными для авторизации
     * @return Строка, которая сериализуется в JSON, где при успешной авторизации status - "ok", token - новый jwt токен, при неуспешной status - "Сообщение пользователю"
     */
    public String logInUser(UserDto userDto);

    /**
     * Проверить на авторизованность пользователя
     * @return Флаг авторизации, где true - пользователь авторизован, false - нет
     */
    public Boolean isLoggedIn();

    /**
     * Добавить нового пользователя в базу данных
     * @param userDto DTO нового пользователя
     * @return Строка, которая сериализуется в JSON, где при успешной регистрации status - "ok", token - новый jwt токен, при неуспешной status - "Сообщение пользователю"
     */
    public String addUser(UserDto userDto);

    /**
     * Удалить пользователя из базы данных с проверкой на удаляющего пользователя
     * Удалить может либо супер администратор, либо клиент, авторизованный, как удаляемый пользователь
     * @param userDto DTO удаляемого пользователя
     */
    public void deleteUser(UserDto userDto);

    /**
     * Получить все продукты, которые лежат в корзине текущего (jwt) пользователя
     * @return Список DTO продуктов
     */
    public List<ProductDto> getProducts();

    /**
     * Удрать продукт из корзины текущего (jwt) пользователя
     * @param id Идентификатор продукта
     */
    public void revokeProduct(int id);
}
