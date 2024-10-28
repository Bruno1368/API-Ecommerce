package com.aplicacao.api.advice;

import com.aplicacao.api.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomResponse> handleNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null, "Elemento não encontrado"));
    }
}
