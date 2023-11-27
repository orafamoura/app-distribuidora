package com.projetoapptabacaria.webtabaca.model.user;

public record RegisterDTO(String login, String password, UserRole role, String email) {
}
