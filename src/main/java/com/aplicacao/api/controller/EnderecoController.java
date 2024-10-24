package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoEndereco;
import com.aplicacao.api.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping("criar")
    public ResponseEntity<HttpStatus> criaEndereco(@Valid @RequestBody DtoEndereco endereco){
        service.criarEndereco(endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
