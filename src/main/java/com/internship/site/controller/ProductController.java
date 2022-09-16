package com.internship.site.controller;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.dto.UserDto;
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
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Получить продукт по идентификатору")
    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @ApiOperation("Получить все продукты, подходящие под параметры запроса")
    @GetMapping("/get-all")
    public List<ProductDto> getAllProducts(@RequestParam(value = "name") String name, @RequestParam(value = "type") String typeName, @RequestParam(value = "country") String countryName) {
        return productService.getAllProducts(name, typeName, countryName);
    }

    @ApiOperation("Добавить новый продукт в базу данных")
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @ApiOperation("Удалить продукт из базы данных")
    @PostMapping("/delete")
    public void deleteProduct(@RequestBody int id) {
        productService.deleteProduct(id);
    }

    @ApiOperation("Получить изображение по идентификатору продукта")
    @ResponseBody
    @GetMapping(value = "/get-img", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@RequestParam(value = "id") int id) throws IOException {
        return productService.getImg(id);
    }

    @ApiOperation("Добавить изображение продукта в папке ресурсов")
    @PostMapping("/add-img")
    public void addImg(@RequestParam(value="img") MultipartFile image) throws IOException {
        productService.addImg(image);
    }

    @ApiOperation("Получить список всех категорий")
    @GetMapping("/types")
    public List<TypeDto> getTypes() {
        return typeService.getTypes();
    }

    @ApiOperation("Получить список всех стран")
    @GetMapping("/countries")
    public List<CountryDto> getCountries() {
        return countryService.getCountries();
    }

    @ApiOperation("Добавить продукт (с идентификатором) в корзину пользователя")
    @PostMapping("/choose/{id}")
    public String chooseProduct(@PathVariable int product_id, @RequestBody UserDto userDto) {
        return productService.chooseProduct(product_id, userDto);
    }
}
