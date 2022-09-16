package com.internship.site.controller;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import com.internship.site.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("Удалить продукт из корзины текущего пользователя")
    @PostMapping("/revoke-product")
    public void revokeProduct(@RequestBody int id) {
        userService.revokeProduct(id);
    }

    @ApiOperation("Получить список продуктов в корзине текущего пользователя")
    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return userService.getProducts();
    }

    @ApiOperation("Получить список всех пользователей")
    @GetMapping("/get-all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation("Совершить авторизацию")
    @PostMapping("/auth")
    public String logInUser(@RequestBody UserDto userDto) throws Exception {
        return userService.logInUser(userDto);
    }

    @ApiOperation("Проверить текущего пользователя на авторизованность")
    @GetMapping("/check-auth")
    public Boolean isLoggedIn() {
        return userService.isLoggedIn();
    }

    @ApiOperation("Добавить нового пользователя в базу данных")
    @PostMapping("/add")
    public String addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @ApiOperation("Получить текущего пользователя")
    @GetMapping("/user")
    public UserDto getUser() {
        return userService.getUser();
    }

    @ApiOperation("Удалить пользователя из базы данных")
    @PostMapping("/delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
    }
}
