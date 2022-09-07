package com.internship.site.controller;

import com.internship.site.domain.JwtRequest;
import com.internship.site.domain.JwtResponse;
import com.internship.site.entity.Token;
import com.internship.site.entity.User;
import com.internship.site.repository.TokenRepo;
import com.internship.site.repository.UserRepo;
import com.internship.site.service.ApiServiceImpl;
import com.internship.site.service.MyUserDetailsService;
import com.internship.site.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    HttpServletRequest request;

    /**
     * Создание нового пользователя
     * @param user новый пользователь
     * @return JSON, который состоит из статуса и токена, при успешном создании статус - ok
     */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        List<User> checkUser = userRepo.findByLogin(user.getLogin());

        if (checkUser.size() > 0) {
            return "{ \"status\": Данный логин уже занят\" }";
        }

        userRepo.save(user);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return "{ \"status\": \"ok\", \"token\": \"" + jwt + "\" }";
    }

    /**
     * Получение пользователя по токену
     */
    @GetMapping("/user/")
    public User isLoginUser() {
        final String authorizationHeader = request.getHeader("Authorization");
        String login = jwtTokenUtil.extractUsername(authorizationHeader);

        User user = userRepo.findByLogin(login).get(0);
        return new User(user.getName(), user.getLogin(), user.getPassword(), user.getEmail());
    }

    @PostMapping("/delete/")
    void deleteUser() {
        final String authorizationHeader = request.getHeader("Authorization");
        String login = jwtTokenUtil.extractUsername(authorizationHeader);

        userRepo.delete(userRepo.findByLogin(login).get(0));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
