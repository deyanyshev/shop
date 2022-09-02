package com.internship.site.repository;

import com.internship.site.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<Country, Integer> {
}
