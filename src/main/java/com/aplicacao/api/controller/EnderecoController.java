package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoAtualizaEndereco;
import com.aplicacao.api.dto.DtoEndereco;
import com.aplicacao.api.dto.DtoEnderecoReponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.EnderecoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService service;
    @PostMapping("/{UsuarioId}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> criar(@Valid @PathVariable Long UsuarioId, @RequestBody DtoEndereco endereco){
        return service.criarEndereco(endereco, UsuarioId);
    }

    @GetMapping()
    public ResponseEntity<Page<CustomResponse<DtoEnderecoReponse>>> enderecos(@PageableDefault(size = 10) Pageable pageable){
        return service.todosEnderecos(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> enderecoId(@PathVariable Long id){
        return service.enderecoPorId(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> atualizar(@PathVariable Long id, @RequestBody DtoAtualizaEndereco endereco) {
        return service.atualizarEndereco(id, endereco);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> excluir(@PathVariable Long id){
        return service.excluir(id);
    }

}
