package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<CustomResponse<DtoClienteResponse>> criaCliente(@Valid @RequestBody DtoCliente dtoCliente){
         return service.criarCliente(dtoCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoClienteResponse>> recuperarCliente(@PathVariable Long id){
        return service.pegarCliente(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoClienteResponse>> atualizarDado(@PathVariable @RequestBody DtoAtualizaCliente dtoAtualizaCliente){
        return service.atualizarCliente(dtoAtualizaCliente);
    }
}
