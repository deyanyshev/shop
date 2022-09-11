package com.internship.site.repository;

import com.internship.site.entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepo extends CrudRepository<Type, Integer> {
    @Override
    List<Type> findAll();

    Type findByName(String name);
}
