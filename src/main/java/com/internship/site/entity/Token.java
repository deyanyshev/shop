package com.internship.site.entity;

import javax.persistence.*;

@Entity
@Table (name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne(mappedBy = "token")
    private User User;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return User;
    }
}
