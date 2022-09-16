package com.internship.site.service.country;

import com.internship.site.dto.CountryDto;

import java.util.List;

public interface CountryService {
    /**
     * Вернуть список стран-производителей из базы данных
     * @return Список DTO стран
     */
    public List<CountryDto> getCountries();
}
