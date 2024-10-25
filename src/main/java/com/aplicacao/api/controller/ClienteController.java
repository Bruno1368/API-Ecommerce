package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/clientes")
    public ResponseEntity<List<CustomResponse<DtoClienteResponse>>> recuperarClientes(){
        return service.pegarClientes();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoClienteResponse>> atualizarCliente(@PathVariable Long id, @RequestBody DtoAtualizaCliente dtoAtualizaCliente){
        return service.atualizarCliente(dtoAtualizaCliente, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoClienteResponse>> excluirCliente(@PathVariable Long id){
        return service.excluir(id);
    }
}
