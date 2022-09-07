package com.internship.site.controller;

import com.internship.site.entity.Product;
import com.internship.site.repository.CountryRepo;
import com.internship.site.repository.ProductRepo;
import com.internship.site.repository.TypeRepo;
import com.internship.site.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private CountryRepo countryRepo;


    @GetMapping("/products")
    public List getProducts() {
        return (List) productRepo.findAll();
    }

    @PostMapping("/add-product")
    public void addProduct(@RequestBody Product product) {
        productRepo.save(product);
    }
}
