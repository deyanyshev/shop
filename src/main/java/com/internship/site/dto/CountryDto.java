package com.internship.site.dto;

import lombok.Data;

/**
 * DTO для отображения стран-производителей продуктов
 *
 * @author deyanyshev
 */

@Data
public class CountryDto {
    int id;
    /**
     * Название страны
     */
    String name;
}
