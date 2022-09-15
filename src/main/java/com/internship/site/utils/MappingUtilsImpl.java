package com.internship.site.utils;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.dto.UserDto;
import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import com.internship.site.entity.user.User;
import liquibase.pro.packaged.O;
import org.springframework.stereotype.Service;

@Service
public class MappingUtilsImpl implements MappingUtils {
    @Override
    public UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public User mapToUserEntity(UserDto dto) {
        return new User(
                dto.getName(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRole()
        );
    }

    @Override
    public ProductDto mapToProductDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImg(product.getImg());
        dto.setCost(product.getCost());
        dto.setType(mapToTypeDto(product.getType()));
        dto.setCountry(mapToCountryDto(product.getCountry()));
        return dto;
    }

    @Override
    public Product mapToProductEntity(ProductDto dto) {
        return new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getImg(),
                dto.getCost(),
                mapToTypeEntity(dto.getType()),
                mapToCountryEntity(dto.getCountry())
        );
    }

    @Override
    public TypeDto mapToTypeDto(Type type) {
        TypeDto dto = new TypeDto();
        dto.setId(type.getId());
        dto.setName(type.getName());
        return dto;
    }

    @Override
    public Type mapToTypeEntity(TypeDto dto) {
        return new Type(
                dto.getName()
        );
    }

    @Override
    public CountryDto mapToCountryDto(Country country) {
        CountryDto dto = new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }

    @Override
    public Country mapToCountryEntity(CountryDto dto) {
        return new Country(
                dto.getName()
        );
    }
}
