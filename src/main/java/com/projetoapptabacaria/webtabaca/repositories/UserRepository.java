package com.projetoapptabacaria.webtabaca.repositories;

import com.projetoapptabacaria.webtabaca.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login); // retorna um userdetails pois vai ser usado pelo security depois
}
