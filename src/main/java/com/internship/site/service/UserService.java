package com.internship.site.service;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<UserDto> getUsers();
    public UserDto getUser();

    public String logInUser(UserDto userDto);

    public Boolean isLoggedIn();

    public String addUser(UserDto userDto);

    public void deleteUser(UserDto userDto);

    public List<ProductDto> getProducts();

    public void revokeProduct(int id);
}
