package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Модель категории продукта
 *
 * @author deyanyshev
 */

@Entity
@Table (name = "types", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Type {
    /** Идентификатор категории */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Список продуктов, которые соответствуют данной категории */
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    /** Наименование категории */
    private String name;

    public Type() {

    }

    public Type(String name) {
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
