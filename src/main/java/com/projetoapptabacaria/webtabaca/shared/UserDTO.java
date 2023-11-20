package com.projetoapptabacaria.webtabaca.shared;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** metodo de visualizacao do cliente
 * @UserDTO retorna o visual pro cliente
 * */
public class UserDTO {

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
