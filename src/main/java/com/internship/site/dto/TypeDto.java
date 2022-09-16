package com.internship.site.dto;

import lombok.Data;

/**
 * DTO для отображения категорий продуктов
 *
 * @author deyanyshev
 */

@Data
public class TypeDto {
    int id;
    /**
     * Название категории
     */
    String name;
}
