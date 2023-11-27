package com.projetoapptabacaria.webtabaca.view.controller;

import com.projetoapptabacaria.webtabaca.model.user.AuthenticationDTO;
import com.projetoapptabacaria.webtabaca.model.user.RegisterDTO;
import com.projetoapptabacaria.webtabaca.model.user.User;
import com.projetoapptabacaria.webtabaca.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    /**
     * metodo de login do usuario
     */
    @PostMapping("/login") //nao e uma boa pratica salvar a senha como string, tem que ser no formato hash, criptografado
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        System.out.println(usernamePassword.toString());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println(auth);
        System.out.println(usernamePassword.toString());

        return ResponseEntity.ok().build();
    }

    /**
     * Metodo para registro do usuario
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build(); // se login for diferente de nulo, bad request

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.email());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
