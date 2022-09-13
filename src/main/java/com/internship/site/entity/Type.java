package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "types", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    private String name;

    public Type() {

    }

    public Type(String name) {
        this.name = name;
    }

    private List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
