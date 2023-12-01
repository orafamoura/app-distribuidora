package com.projetoapptabacaria.webtabaca.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projetoapptabacaria.webtabaca.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Metodo de geracao do token para o usuario
     * @param user usado para associar o token ao user
     */
    public String generateToken(User user){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api") //nome da aplicacao qualquer nome
                    .withSubject(user.getLogin()) //Subject seria o usuario, salva o login do usuario no token
                    .withExpiresAt(genExpirationDate()) // tempo de expiracao
                    .sign(algorithm);
        } catch(JWTCreationException e){
            throw new RuntimeException("Error while generation token", e); //TRATAR EXCEPTION
        }
    }

    /**
     * Metodo de validacao do token
     * @param token a ser validado
     * @return um token verificado
     */
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch(JWTVerificationException e){
            return "";

        }
    }

    /**
     * metodo de criacao do tempo para expirar o token
     * @return o tempo limite do token
     */
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }



}


