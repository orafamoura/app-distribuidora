package com.projetoapptabacaria.webtabaca.view.controller.model.user;

public class UserRequest {
    private String username;

    private Long password;

    private Long email;

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
