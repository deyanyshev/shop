package com.internship.site.controller;

import com.internship.site.entity.Token;
import com.internship.site.entity.User;
import com.internship.site.repository.TokenRepo;
import com.internship.site.repository.UserRepo;
import com.internship.site.service.ApiService;
import com.internship.site.service.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @GetMapping("/{id}")
    @ResponseBody
    public User getUser(@PathVariable int id) {
        return userRepo.findById(id).get(0);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        List<User> checkUser = userRepo.findByLogin(user.getLogin());

        if (checkUser.size() > 0) {
            return "{ \"status\": Данный логин уже занят\" }";
        }

        userRepo.save(user);

        String string_token = (new ApiServiceImpl()).generateToken(18);
        Token token = new Token(string_token);
        tokenRepo.save(token);

        user.setToken(token);
        userRepo.save(user);

        return "{ \"status\": \"ok\" }";
    }

    @PostMapping("/auth")
    public String authUser(@RequestBody User user) {
        List<User> res = userRepo.findByLoginAndPassword(user.getLogin(), user.getPassword());

        if (res.size() == 1) {
            String string_token = (new ApiServiceImpl()).generateToken(18);
            Token token = new Token(string_token);
            tokenRepo.save(token);

            User myUser = res.get(0);
            myUser.setToken(token);
            userRepo.save(myUser);

            return "{ \"status\": \"ok\" }";
        }

        return "{ \"status\": \"Логин или пароль неверный\" }";
    }

    @PostMapping("/delete/{id}")
    void deleteUser(@PathVariable int id) {
        User user = userRepo.findById(id).get(0);
        userRepo.delete(user);
    }
}
