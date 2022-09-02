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
    void addUser(@RequestBody User user) {
        System.out.println(user.getId());
        userRepo.save(user);

        String string_token = (new ApiServiceImpl()).generateToken(18);
        Token token = new Token(string_token);
        tokenRepo.save(token);

        user.setToken(token);
        userRepo.save(user);
    }

    @GetMapping("/auth")
    public Boolean authUser(@RequestParam String login, @RequestParam String password) {
        List<User> res = userRepo.findByLoginAndPassword(login, password);

        if (res.size() == 1) {
            String string_token = (new ApiServiceImpl()).generateToken(18);
            Token token = new Token(string_token);
            tokenRepo.save(token);

            User user = res.get(0);
            user.setToken(token);
            userRepo.save(user);

            return true;
        }

        return false;
    }

    @PostMapping("/delete/{id}")
    void deleteUser(@PathVariable int id) {
        User user = userRepo.findById(id).get(0);
        userRepo.delete(user);
    }
}
