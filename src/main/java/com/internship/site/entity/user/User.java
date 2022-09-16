package com.internship.site.entity.user;

import com.internship.site.entity.Purchase;
import com.internship.site.entity.enums.Role;

import javax.persistence.*;
import java.util.List;


/**
 * Модель пользователя
 *
 * @author deyanyshev
 */

@Entity
@Table (name = "users", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "login")
        }
)
public class User {
    /** Идентификатор пользователя */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Имя пользователя */
    private String name;

    /** Логин пользователя (уникальный) */
    private String login;

    /** Пароль пользователя */
    private String password;

    /** Почта пользователя */
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;

    public User() {
    }

    public User(String name, String login, String password, String email, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
