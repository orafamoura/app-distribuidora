package com.projetoapptabacaria.webtabaca.handler;

import com.projetoapptabacaria.webtabaca.model.error.ErrorMessage;
import com.projetoapptabacaria.webtabaca.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //controlador dentro da nossa aplicacao
public class RestExceptionHandler {

    /**
     * excecao para nao encontrado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e) {
        ErrorMessage error = new ErrorMessage("Not found", HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
