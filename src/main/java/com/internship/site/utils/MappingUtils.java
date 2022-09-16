package com.internship.site.utils;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.dto.UserDto;
import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import com.internship.site.entity.user.User;
import lombok.experimental.UtilityClass;


@UtilityClass
public class MappingUtils {

    /**
     * Преобразовать модель пользователя в DTO пользователя
     * @param user Модель пользователя
     * @return DTO пользователя
     */
    public static UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    /**
     * Преобразовать DTO пользователя в модель пользователя
     * @param dto DTO пользователя
     * @return Модель пользователя
     */
    public User mapToUserEntity(UserDto dto) {
        return new User(
                dto.getName(),
                dto.getLogin(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRole()
        );
    }

    /**
     * Преобразовать модель продукта в DTO продукта
     * @param product Модель продукта
     * @return DTO продукта
     */
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

    /**
     * Преобразовать DTO продукта в модель продукта
     * @param dto DTO продукта
     * @return Модель продукта
     */
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

    /**
     * Преобразовать модель категории в DTO категории
     * @param type Модель категории
     * @return DTO категории
     */
    public TypeDto mapToTypeDto(Type type) {
        TypeDto dto = new TypeDto();
        dto.setId(type.getId());
        dto.setName(type.getName());
        return dto;
    }

    /**
     * Преобразовать DTO категории в модель категории
     * @param dto DTO категории
     * @return Модель категории
     */
    public Type mapToTypeEntity(TypeDto dto) {
        return new Type(
                dto.getName()
        );
    }

    /**
     * Преобразовать модель страны в DTO страны
     * @param country Модель страны
     * @return DTO страны
     */
    public CountryDto mapToCountryDto(Country country) {
        CountryDto dto = new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }

    /**
     * Преобразовать DTO страны в модель страны
     * @param dto DTO страны
     * @return Модель страны
     */
    public Country mapToCountryEntity(CountryDto dto) {
        return new Country(
                dto.getName()
        );
    }
}
