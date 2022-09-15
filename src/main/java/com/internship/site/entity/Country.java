package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "countries", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "name")
        }
)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

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
