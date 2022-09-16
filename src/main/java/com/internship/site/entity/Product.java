package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Модель продукта
 *
 * @author deyanyshev
 */

@Entity
@Table (name = "products")
public class Product {
    /** Идентификатор продукта */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Категория, к которой принадлежит продукт */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    /** Наименование продукта */
    private String name;

    /** Имя файла изображения продукта */
    private String img;

    /** Описание к продукту */
    @Column(length=1000)
    private String description;

    /** Стоимость продукта */
    private int cost;

    /** Страна-производитель продукта */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    /** Список покупок (корзин), в которых находится данный товар */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> products;

    public Product() {
    }

    public Product(String name, String description, String img, int cost, Type type, Country country) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.cost = cost;
        this.type = type;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }

    public List<Purchase> getProducts() {
        return products;
    }
}
