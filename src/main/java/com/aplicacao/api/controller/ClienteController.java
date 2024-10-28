package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.ClienteService;
import com.aplicacao.api.validator.ValidaEmail;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<CustomResponse<DtoClienteResponse>> criar(@Valid @RequestBody DtoCliente dtoCliente){
        return service.criarCliente(dtoCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoClienteResponse>> recuperar(@PathVariable Long id){
        return service.cliente(id);
    }

    @GetMapping()
    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>> todosClientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return service.todosClientes(paginacao);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>> clientesAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return service.clientesAtivos(page);
    }

    @GetMapping("/email")
    public ResponseEntity<CustomResponse<DtoClienteResponse>> clientePorEmail(@RequestParam String email){
        if(email == null || email.trim().isEmpty()){
            return ResponseEntity.badRequest().body(new CustomResponse<>(null, "O parâmetro 'email' é obrigatório."));
        }
        if(!ValidaEmail.isValidEmail(email)){
            return ResponseEntity.badRequest().body(new CustomResponse<>(null, "Formato inválido de email"));
        }
        return service.clientesPorEmail(email);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>> clientesInativos(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return service.clientesInativos(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoClienteResponse>> atualizar(@PathVariable Long id, @RequestBody DtoAtualizaCliente dtoAtualizaCliente){
        return service.atualizarCliente(dtoAtualizaCliente, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoClienteResponse>> excluir(@PathVariable Long id){
        return service.excluir(id);
    }
}
