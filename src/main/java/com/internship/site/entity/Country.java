package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Модель страны-производителя
 *
 * @author deyanyshev
 */

@Entity
@Table (name = "countries", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Country {
    /** Идентификатор страны */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Список продуктов, произведённых в данной стране */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    /** Наименование страны */
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
