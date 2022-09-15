package com.internship.site.service;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public ProductDto getProduct(int id);

    public List<ProductDto> getAllProducts(String name, String typeName, String countryName);

    public void addProduct(ProductDto productDto);

    public void deleteProduct(int id);

    public byte[] getImg(int id) throws IOException;

    public void addImg(MultipartFile image) throws IOException;
}
