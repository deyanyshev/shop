package com.internship.site.service.user;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import com.internship.site.entity.Purchase;
import com.internship.site.entity.enums.Role;
import com.internship.site.entity.user.User;
import com.internship.site.jwt.JwtUtil;
import com.internship.site.repository.ProductRepo;
import com.internship.site.repository.PurchaseRepo;
import com.internship.site.repository.UserRepo;
import com.internship.site.service.MyUserDetailsService;
import com.internship.site.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;

    @Override
    public List<UserDto> getUsers() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        if (myUser.getRole() == Role.ROLE_ADMINISTRATOR || myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
            List<User> users = userRepo.findAllByOrderByIdAsc();
            List<UserDto> usersDto = new ArrayList<>();

            for (User user: users) {
                usersDto.add(MappingUtils.mapToUserDto(user));
            }
            return usersDto;
        }

        return null;
    }

    public UserDto getUser() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User user = userRepo.findByLogin(login);
        return MappingUtils.mapToUserDto(user);
    }

    public String logInUser(UserDto userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword())
            );
        }
        catch (Exception e) {
            return "{ \"status\": \"???????????????? ?????????? ?????? ????????????\" }";
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return "{ \"status\": \"ok\", \"token\": \"" + jwt + "\" }";
    }

    public Boolean isLoggedIn() {
        try {
            final String authorizationHeader = request.getHeader("Authorization");
            String jwt = authorizationHeader.substring(7);

            return jwtTokenUtil.validateToken(jwt, userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(jwt)));
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public String addUser(UserDto userDto) {
        User checkUser = userRepo.findByLogin(userDto.getLogin());

        if (checkUser != null) {
            return "{ \"status\": \"`???????????? ?????????? ?????? ??????????\" }";
        }

        if (request.getHeader("Authorization") != null && userDto.getRole() == Role.ROLE_ADMINISTRATOR) {
            try {
                final String authorizationHeader = request.getHeader("Authorization");
                String jwt = authorizationHeader.substring(7);

                String login = jwtTokenUtil.extractUsername(jwt);
                User myUser = userRepo.findByLogin(login);

                if (myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
                    userDto.setRole(Role.ROLE_ADMINISTRATOR);
                } else {
                    userDto.setRole(Role.ROLE_CLIENT);
                }
            } catch (Exception err) {
                userDto.setRole(Role.ROLE_CLIENT);
            }
        } else {
            userDto.setRole(Role.ROLE_CLIENT);
        }

        userRepo.save(MappingUtils.mapToUserEntity(userDto));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getLogin());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return "{ \"status\": \"ok\", \"token\": \"" + jwt + "\" }";
    }

    @Override
    public void deleteUser(UserDto userDto) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        /** ???????????????? ???????????????????? ???????????????????????? */
        if (Objects.equals(login, userDto.getLogin()) || myUser.getRole() == Role.ROLE_SUPER_ADMINISTRATOR) {
            userRepo.delete(userRepo.findByLogin(userDto.getLogin()));
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);
        List<Purchase> purchases = purchaseRepo.findPurchasesByUser(myUser);

        List<ProductDto> productsDto = new ArrayList<>();

        for (Purchase purchase: purchases) {
            productsDto.add(MappingUtils.mapToProductDto(productRepo.findById(purchase.getProduct().getId())));
        }
        return productsDto;
    }

    @Override
    public void revokeProduct(int id) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);

        String login = jwtTokenUtil.extractUsername(jwt);
        User myUser = userRepo.findByLogin(login);

        purchaseRepo.deleteByUserAndProduct(myUser, productRepo.findById(id));
    }
}
