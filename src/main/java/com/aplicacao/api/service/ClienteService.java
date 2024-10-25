package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity<CustomResponse<DtoClienteResponse>> atualizarCliente(DtoAtualizaCliente dtoAtualizaCliente, Long id) {
        Optional<Cliente> clienteRecuperado = repository.findById(id);
        if(!clienteRecuperado.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteRecuperado.get();
        cliente.atualizaDados(dtoAtualizaCliente);
        repository.save(cliente);

        return ResponseEntity.ok().body(new CustomResponse<>(new DtoClienteResponse(cliente), null));
    }

    public ResponseEntity<CustomResponse<DtoClienteResponse>> excluir(Long id) {
        Optional<Cliente> clienteRecuperado = repository.findById(id);
        if(!clienteRecuperado.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteRecuperado.get();
        cliente.excluirCliente();
        repository.save(cliente);
        return ResponseEntity.ok().body(new CustomResponse<>(null, "Cliente excluído com sucesso"));
    }

    public ResponseEntity<List<CustomResponse<DtoClienteResponse>>> pegarClientes() {
        List<Cliente> clientes = repository.findAllByAtivoTrue();
        List<CustomResponse<DtoClienteResponse>> response = clientes.stream().map(c -> new DtoClienteResponse(c)).map(dtoCliente -> new CustomResponse<>(dtoCliente, null)).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}
