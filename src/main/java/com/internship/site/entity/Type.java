package com.internship.site.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    private String name;

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
