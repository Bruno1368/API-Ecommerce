package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public ResponseEntity<DtoClienteResponse> criarCliente(DtoCliente dtoCliente) {
        Cliente cliente = Cliente.toEntity(dtoCliente);
        DtoClienteResponse clienteResponse = new DtoClienteResponse(cliente);
        if(!repository.existsByEmail(cliente.getEmail())){
            repository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteResponse);
    }
}
