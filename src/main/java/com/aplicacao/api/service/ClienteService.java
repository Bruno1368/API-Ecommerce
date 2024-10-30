package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public ResponseEntity<CustomResponse<DtoClienteResponse>> cliente(Long id) {
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

        return ResponseEntity.ok().body(new CustomResponse<>(new DtoClienteResponse(cliente), "Cliente atualizado"));
    }

    public ResponseEntity<CustomResponse<DtoClienteResponse>> excluir(Long id) {
        Optional<Cliente> clienteRecuperado = repository.findById(id);
        if(!clienteRecuperado.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteRecuperado.get();
        cliente.desativar();
        repository.save(cliente);
        return ResponseEntity.ok().body(new CustomResponse<>(null, "Cliente excluído com sucesso"));
    }

    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>>todosClientes(Pageable paginacao) {
        Page<Cliente> clientesPage = repository.findAll(paginacao);
        var response = clientesPage.map(c -> new DtoClienteResponse(c)).map(dtoCliente -> new CustomResponse<>(dtoCliente, null));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>> clientesAtivos(Pageable paginacao) {
        Page<Cliente> clientesPage = repository.findAllByAtivoTrue(paginacao);
        var response = clientesPage.map(c -> new DtoClienteResponse(c)).map(dtoCliente -> new CustomResponse<>(dtoCliente, null));
        return ResponseEntity.ok().body(response);

    }

    public ResponseEntity<Page<CustomResponse<DtoClienteResponse>>> clientesInativos(Pageable page) {
        Page<Cliente> clientesPage = repository.findAllByAtivoFalse(page);
        var response = clientesPage.map(c -> new DtoClienteResponse(c)).map(dtoCliente -> new CustomResponse<>(dtoCliente, null));
        return ResponseEntity.ok().body(response);
    }


    public ResponseEntity<CustomResponse<DtoClienteResponse>> clientesPorEmail(String email) {
         Optional<Cliente> clienteOptional = repository.findByEmail(email);
         if(!clienteOptional.isPresent()){
             throw new NoSuchElementException();
         }
         Cliente cliente = clienteOptional.get();
         return ResponseEntity.ok().body(new CustomResponse<>(new DtoClienteResponse(cliente), null));
    }
}
