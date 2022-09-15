package com.internship.site.service;

import com.internship.site.dto.CountryDto;
import com.internship.site.entity.Country;
import com.internship.site.repository.CountryRepo;
import com.internship.site.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private MappingUtils mappingUtils;

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<CountryDto> getCountries() {
        List<Country> countries = countryRepo.findAll();
        List<CountryDto> countriesDto = new ArrayList<>();

        for (Country country: countries) {
            countriesDto.add(mappingUtils.mapToCountryDto(country));
        }

        return countriesDto;
    }
}
