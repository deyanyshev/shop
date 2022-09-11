package com.internship.site.repository;

import com.internship.site.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepo extends CrudRepository<Country, Integer> {
    @Override
    List<Country> findAll();

    Country findByName(String name);
}
