package com.internship.site.service;

import com.internship.site.dto.CountryDto;
import com.internship.site.dto.ProductDto;
import com.internship.site.dto.TypeDto;
import com.internship.site.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    /**
     * Получить продукт по идентификатору
     * @param id Идентификатор продукта
     * @return DTO продукта
     */
    public ProductDto getProduct(int id);

    /**
     * Получить все продукты, подходящие под параметры запроса
     * @param name Подотрезок строк наименований продуктов
     * @param typeName Наименование категории продуктов
     * @param countryName Наименование страны-производителя продуктов
     * @return
     */
    public List<ProductDto> getAllProducts(String name, String typeName, String countryName);

    /**
     * Добавить новый продукт в базу данных
     * Если категории нового продукта не существует в базе данных, то создаст новую категорию в базу данных
     * Аналогично со странами
     * @param productDto DTO нового продукта
     */
    public void addProduct(ProductDto productDto);

    /**
     * Удалить продукт из базы данных
     * @param id Идентификатор продукта
     */
    public void deleteProduct(int id);

    /**
     * Получить изображение продукта
     * @param id Идентификатор продукта
     * @return Массив байт
     * @throws IOException
     */
    public byte[] getImg(int id) throws IOException;

    /**
     * Добавить изображение в папку ресурсов
     * @param image Изображение
     * @throws IOException
     */
    public void addImg(MultipartFile image) throws IOException;

    /**
     * Добавить продукт в коризину
     * @param id Идентификатор продукта
     * @param userDto DTO пользователя, добавляющего продукт в корзину
     * @return Строка, которая сериализуется в JSON, где при успешном добавлении status - "ok", при неуспешной status - "Сообщение пользователю"
     */
    public String chooseProduct(int id, UserDto userDto);
}
