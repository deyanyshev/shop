package com.internship.site.controller;

import com.internship.site.entity.Product;
import com.internship.site.entity.enums.Role;
import com.internship.site.entity.user.User;
import com.internship.site.repository.ProductRepo;
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

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/add")
    public void addProduct(Product product) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);

        Role role = userRepo.findByLogin(login).get(0).getRole();

        if (role == Role.ROLE_ADMINISTRATOR || role == Role.ROLE_SUPER_ADMINISTRATOR) {
            productRepo.save(product);
        }
    }
}
