package com.internship.site.controller;

import com.internship.site.dto.UserDto;
import com.internship.site.entity.user.User;
import com.internship.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

/*
    @PostMapping("/revoke-product")
    public void revokeProduct(@RequestBody int id) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        userRepo.deleteProduct(id, myUser.getId());
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);
        List<Product> products = myUser.getProducts();

        for (Product product: products) {
            product.setUsers(null);
            product.setType(new Type(product.getType().getName()));
            product.setCountry(new Country(product.getCountry().getName()));
        }
        return products;
    }*/

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
