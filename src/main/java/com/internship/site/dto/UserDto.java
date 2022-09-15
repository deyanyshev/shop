package com.internship.site.dto;

import com.internship.site.entity.Product;
import com.internship.site.entity.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    int id;
    String name;
    String login;
    String password;
    String email;
    Role role;
}
