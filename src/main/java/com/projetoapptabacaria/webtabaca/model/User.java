package com.projetoapptabacaria.webtabaca.model;

import com.projetoapptabacaria.webtabaca.repositories.UserRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Long password;

    private Long email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }
}
