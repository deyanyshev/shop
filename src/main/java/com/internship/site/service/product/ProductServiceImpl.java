package com.internship.site.service.product;

import com.internship.site.dto.ProductDto;
import com.internship.site.dto.UserDto;
import com.internship.site.entity.Country;
import com.internship.site.entity.Product;
import com.internship.site.entity.Purchase;
import com.internship.site.entity.Type;
import com.internship.site.entity.enums.Role;
import com.internship.site.jwt.JwtUtil;
import com.internship.site.repository.*;
import com.internship.site.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${resourcePath}")
    private String resourcePath;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TypeRepo typeRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;
    @Autowired
    HttpServletRequest request;

    @Override
    public ProductDto getProduct(int id) {
        Product product = productRepo.findById(id);
        return MappingUtils.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts(String name, String typeName, String countryName) {
        List<Product> products;

        Type type = typeRepo.findByName(typeName);
        Country country = countryRepo.findByName(countryName);
        name = "%" + name + "%";

        if (type != null) {
            if (country != null) {
                products = productRepo.findAllByNameLikeAndTypeAndCountry(name, type, country);
            } else {
                products = productRepo.findAllByNameLikeAndType(name, type);
            }
        } else {
            if (country != null) {
                products = productRepo.findAllByNameLikeAndCountry(name, country);
            } else {
                products = productRepo.findAllByNameLike(name);
            }
        }

        List<ProductDto> productsDto = new ArrayList<>();

        for (Product product : products) {
            productsDto.add(MappingUtils.mapToProductDto(product));
        }
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);

        Role role = userRepo.findByLogin(login).getRole();

        if (role == Role.ROLE_ADMINISTRATOR || role == Role.ROLE_SUPER_ADMINISTRATOR) {
            Type type = typeRepo.findByName(productDto.getType().getName());
            Country country = countryRepo.findByName(productDto.getCountry().getName());

            if (type == null) {
                type = typeRepo.save(MappingUtils.mapToTypeEntity(productDto.getType()));
            }

            if (country == null) {
                country = countryRepo.save(MappingUtils.mapToCountryEntity(productDto.getCountry()));
            }

            Product product = MappingUtils.mapToProductEntity(productDto);
            product.setType(type);
            product.setCountry(country);
            productRepo.save(product);
        }
    }

    @Override
    public void deleteProduct(int id) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);

        Role role = userRepo.findByLogin(login).getRole();

        if (role == Role.ROLE_ADMINISTRATOR || role == Role.ROLE_SUPER_ADMINISTRATOR) {
            productRepo.delete(productRepo.findById(id));
        }
    }

    @Override
    public byte[] getImg(int id) throws IOException {
        Product product = productRepo.findById(id);
        Path path = Paths.get(resourcePath + product.getImg());

        return Files.readAllBytes(path);
    }

    @Override
    public void addImg(MultipartFile image) throws IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String login = jwtTokenUtil.extractUsername(jwt);
        Role role = userRepo.findByLogin(login).getRole();

        if (role == Role.ROLE_ADMINISTRATOR || role == Role.ROLE_SUPER_ADMINISTRATOR) {
            Files.write(Paths.get(resourcePath + image.getOriginalFilename()), image.getBytes());
        }
    }

    @Override
    public String chooseProduct(int id, UserDto userDto) {
        try {
            Purchase purchase = new Purchase(userRepo.findByLogin(userDto.getLogin()), productRepo.findById(id));
            purchaseRepo.save(purchase);
            return "{ \"status\": \"ok\" }";
        } catch (Exception err) {
            return "{ \"status\": \"Продукт уже есть в корзине\" }";
        }
    }
}
