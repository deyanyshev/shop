package com.internship.site.repository;

import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findAllByNameLike(String name);
    List<Product> findAllByNameLikeAndType(String name, Type type);
    List<Product> findAllByNameLikeAndCountry(String name, Country country);
    List<Product> findAllByNameLikeAndTypeAndCountry(String name, Type type, Country country);
    List<Product> findAllByType(Type type);
    List<Product> findAllByCountry(Country country);
}
