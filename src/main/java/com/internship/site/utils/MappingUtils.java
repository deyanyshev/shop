package com.internship.site.utils;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.dto.UserDto;
import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import com.internship.site.entity.user.User;
import org.springframework.stereotype.Service;

public interface MappingUtils {
    public UserDto mapToUserDto(User user);

    public User mapToUserEntity(UserDto dto);

    public ProductDto mapToProductDto(Product product);

    public Product mapToProductEntity(ProductDto dto);

    public TypeDto mapToTypeDto(Type type);

    public Type mapToTypeEntity(TypeDto dto);

    public CountryDto mapToCountryDto(Country country);

    public Country mapToCountryEntity(CountryDto dto);
}
