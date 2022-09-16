package com.internship.site.service.type;

import com.internship.site.dto.TypeDto;

import java.util.List;

public interface TypeService {
    /**
     * Вернуть все категории из базы данных
     * @return Список DTO категорий
     */
    public List<TypeDto> getTypes();
}
