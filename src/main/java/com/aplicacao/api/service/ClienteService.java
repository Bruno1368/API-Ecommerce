package com.aplicacao.api.service;

import com.aplicacao.api.controller.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;


    public ResponseEntity<CustomResponse<DtoClienteResponse>> criarCliente(DtoCliente dtoCliente) {
        Cliente cliente = Cliente.toEntity(dtoCliente);
        DtoClienteResponse clienteResponse = new DtoClienteResponse(cliente);
        if(!repository.existsByEmail(cliente.getEmail())){
            repository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse<>(clienteResponse, "Cliente criado com sucesso"));
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>(null, "Cliente já cadastrado"));
    }


    public ResponseEntity<CustomResponse<DtoClienteResponse>> pegarCliente(Long id) {
        Optional<Cliente> clienteRecuperado = repository.findById(id);
        if(!clienteRecuperado.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteRecuperado.get();
        return ResponseEntity.ok().body(new CustomResponse<>(new DtoClienteResponse(cliente), null));

    }

    public ResponseEntity<CustomResponse<DtoClienteResponse>> atualizarCliente(DtoAtualizaCliente dtoAtualizaCliente) {

    }
}
