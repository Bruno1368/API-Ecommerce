package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoEndereco;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.model.Endereco;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;
    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<HttpStatusCode> criarEndereco(DtoEndereco dtoEndereco){
        Endereco endereco = Endereco.toEntity(dtoEndereco);
        Optional<Cliente> clienteOptional = clienteRepository.findById(dtoEndereco.idCliente());
        if(!clienteOptional.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteOptional.get();
        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        repository.save(endereco);

        return ResponseEntity.ok().build();
    }
}
