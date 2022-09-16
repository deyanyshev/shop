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
    /**
     * Преобразовать модель пользователя в DTO пользователя
     * @param user Модель пользователя
     * @return DTO пользователя
     */
    public UserDto mapToUserDto(User user);

    /**
     * Преобразовать DTO пользователя в модель пользователя
     * @param dto DTO пользователя
     * @return Модель пользователя
     */
    public User mapToUserEntity(UserDto dto);

    /**
     * Преобразовать модель продукта в DTO продукта
     * @param product Модель продукта
     * @return DTO продукта
     */
    public ProductDto mapToProductDto(Product product);

    /**
     * Преобразовать DTO продукта в модель продукта
     * @param dto DTO продукта
     * @return Модель продукта
     */
    public Product mapToProductEntity(ProductDto dto);

    /**
     * Преобразовать модель категории в DTO категории
     * @param type Модель категории
     * @return DTO категории
     */
    public TypeDto mapToTypeDto(Type type);

    /**
     * Преобразовать DTO категории в модель категории
     * @param dto DTO категории
     * @return Модель категории
     */
    public Type mapToTypeEntity(TypeDto dto);

    /**
     * Преобразовать модель страны в DTO страны
     * @param country Модель страны
     * @return DTO страны
     */
    public CountryDto mapToCountryDto(Country country);

    /**
     * Преобразовать DTO страны в модель страны
     * @param dto DTO страны
     * @return Модель страны
     */
    public Country mapToCountryEntity(CountryDto dto);
}
