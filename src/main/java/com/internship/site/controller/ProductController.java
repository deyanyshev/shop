package com.internship.site.controller;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Type;
import com.internship.site.entity.enums.Role;
import com.internship.site.entity.user.User;
import com.internship.site.repository.CountryRepo;
import com.internship.site.repository.ProductRepo;
import com.internship.site.repository.TypeRepo;
import com.internship.site.repository.UserRepo;
import com.internship.site.service.CountryService;
import com.internship.site.service.MyUserDetailsService;
import com.internship.site.jwt.JwtUtil;
import com.internship.site.service.ProductService;
import com.internship.site.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private CountryRepo countryRepo;


    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    /*
    @PostMapping("/choose/{id}")
    public String chooseProduct(@PathVariable int id, @RequestBody User user) {
        try {
            userRepo.setProduct(id, userRepo.findByLogin(user.getLogin()).getId());
            return "{ \"status\": \"ok\" }";
        } catch (Exception err) {
            return "{ \"status\": \"Продукт уже есть в корзине\" }";
        }
    }*/

    @GetMapping("/get-all")
    public List<ProductDto> getAllProducts(@RequestParam(value = "name") String name, @RequestParam(value = "type") String typeName, @RequestParam(value = "country") String countryName) {
        return productService.getAllProducts(name, typeName, countryName);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PostMapping("/delete")
    public void deleteProduct(@RequestBody int id) {
        productService.deleteProduct(id);
    }

    @ResponseBody
    @GetMapping(value = "/get-img", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@RequestParam(value = "id") int id) throws IOException {
        return productService.getImg(id);
    }

    @PostMapping("/add-img")
    public void addImg(@RequestParam(value="img") MultipartFile image) throws IOException {
        productService.addImg(image);
    }

    @GetMapping("/types")
    public List<TypeDto> getTypes() {
        return typeService.getTypes();
    }

    @GetMapping("/countries")
    public List<CountryDto> getCountries() {
        return countryService.getCountries();
    }
}
