package com.internship.site.entity;

import com.internship.site.entity.user.User;

import javax.persistence.*;

/**
 * Модель покупок (корзина)
 * Связующая таблица между пользователями и продуктами (многие ко многим)
 *
 * @author deyanyshev
 */

@Entity
@Table(name = "purchases")
public class Purchase {
    /** Идентификатор покупки */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Пользователь, добавивший продукт в корзину */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /** Продукт, добавленный в корзину */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Purchase() {
    }

    public Purchase(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
