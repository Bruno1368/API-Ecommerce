package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoEndereco;
import com.aplicacao.api.dto.DtoEnderecoReponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.EnderecoService;
import jakarta.transaction.Transactional;
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
    @PostMapping("/criar/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> criaEndereco(@Valid @PathVariable Long id, @RequestBody DtoEndereco endereco){
        return service.criarEndereco(endereco, id);
    }
}
