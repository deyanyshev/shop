package com.internship.site.dto;

import lombok.Data;

/**
 * DTO для отображения продуктов
 *
 * @author deyanyshev
 */

@Data
public class ProductDto {
    int id;
    /**
     * Название продукта
     */
    String name;
    /**
     * Описание к продукту
     */
    String description;
    /**
     * Название файла картинки продукта
     */
    String img;
    /**
     * Стоимость продукта
     */
    int cost;
    /**
     * категория продукта
     */
    TypeDto type;
    /**
     * Страна-производитель продукта
     */
    CountryDto country;
}
