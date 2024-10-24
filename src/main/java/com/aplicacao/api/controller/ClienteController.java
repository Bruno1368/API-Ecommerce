package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping("criar")
    public ResponseEntity<DtoClienteResponse> criaCliente(@Valid @RequestBody DtoCliente dtoCliente){
        return service.criarCliente(dtoCliente);
    }
}
