package com.internship.site.controller;

import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import com.internship.site.entity.enums.Role;
import com.internship.site.entity.user.User;
import com.internship.site.repository.UserRepo;
import com.internship.site.service.MyUserDetailsService;
import com.internship.site.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    HttpServletRequest request;

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
    }

    @GetMapping("/get-all")
    public List<User> getUsers() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        if (myUser.getRole() == Role.ROLE_ADMINISTRATOR || myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
            List<User> users = userRepo.findAllByOrderByIdAsc();
            for (User user: users) {
                user.setProducts(null);
            }
            return users;
        }

        return null;
    }

    @GetMapping("/check-auth")
    public Boolean isLoggedIn() {
        try {
            final String authorizationHeader = request.getHeader("Authorization");
            String jwt = authorizationHeader.substring(7);

            return jwtTokenUtil.validateToken(jwt, userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(jwt)));
        } catch (Exception err) {
            return false;
        }
    }

    /**
     * Создание нового пользователя
     * @param user новый пользователь
     * @return JSON, который состоит из статуса и токена, при успешном создании статус - ok
     */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        User checkUser = userRepo.findByLogin(user.getLogin());

        if (checkUser != null) {
            return "{ \"status\": \"`Данный логин уже занят\" }";
        }

        if (request.getHeader("Authorization") != null && user.getRole() == Role.ROLE_ADMINISTRATOR) {
            try {
                final String authorizationHeader = request.getHeader("Authorization");
                String jwt = authorizationHeader.substring(7);

                String login = jwtTokenUtil.extractUsername(jwt);
                User myUser = userRepo.findByLogin(login);

                if (myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
                    user.setRole(Role.ROLE_ADMINISTRATOR);
                } else {
                    user.setRole(Role.ROLE_CLIENT);
                }
            } catch (Exception err) {
                user.setRole(Role.ROLE_CLIENT);
            }
        } else {
            user.setRole(Role.ROLE_CLIENT);
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
    @GetMapping("/user")
    public User getUser() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User user = userRepo.findByLogin(login);

        return new User(user.getName(), user.getLogin(), user.getPassword(), user.getEmail(), user.getRole());
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        if (Objects.equals(login, user.getLogin()) || myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
            userRepo.delete(user);
        }
    }

    @PostMapping("/auth")
    public String logInUser(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
            );
        }
        catch (Exception e) {
            return "{ \"status\": \"Неверный логин или пароль\" }";
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return "{ \"status\": \"ok\", \"token\": \"" + jwt + "\" }";
    }
}
