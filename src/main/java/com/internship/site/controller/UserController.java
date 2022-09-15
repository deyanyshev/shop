package com.internship.site.controller;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import com.internship.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/revoke-product")
    public void revokeProduct(@RequestBody int id) {
        userService.revokeProduct(id);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return userService.getProducts();
    }

    @GetMapping("/get-all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/auth")
    public String logInUser(@RequestBody UserDto userDto) throws Exception {
        return userService.logInUser(userDto);
    }

    @GetMapping("/check-auth")
    public Boolean isLoggedIn() {
        return userService.isLoggedIn();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
    @GetMapping("/user")
    public UserDto getUser() {
        return userService.getUser();
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
    }
}
