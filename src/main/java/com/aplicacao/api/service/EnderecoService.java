package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoAtualizaEndereco;
import com.aplicacao.api.dto.DtoEndereco;
import com.aplicacao.api.dto.DtoEnderecoReponse;
import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.model.Endereco;
import com.aplicacao.api.repository.ClienteRepository;
import com.aplicacao.api.repository.EnderecoRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> criarEndereco(DtoEndereco dtoEndereco, Long id){
        Endereco endereco = Endereco.toEntity(dtoEndereco);
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(!clienteOptional.isPresent()){
            throw new NoSuchElementException();
        }
        Cliente cliente = clienteOptional.get();
        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        repository.save(endereco);

        return ResponseEntity.ok().body(new CustomResponse<>(null, "Endereço criado com sucesso"));
    }


    public ResponseEntity<Page<CustomResponse<DtoEnderecoReponse>>> todosEnderecos(Pageable pageable) {
        Page<Endereco> endereco = repository.findAll(pageable);
        var response = endereco.map(e -> new DtoEnderecoReponse(e)).map(dtoEndereco -> new CustomResponse<>(dtoEndereco, null));
        return ResponseEntity.ok().body(response);
    }


    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> atualizarEndereco(Long id, DtoAtualizaEndereco dtoAtualizaEndereco) {
        Optional<Endereco> enderecoOptional = repository.findById(id);
        if(!enderecoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        Endereco endereco = enderecoOptional.get();
        endereco.atualizaDados(dtoAtualizaEndereco);
        repository.save(endereco);
        return ResponseEntity.ok().body(new CustomResponse<>(new DtoEnderecoReponse(endereco), "Endereço atualizado com sucesso"));
    }


    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> excluir(Long id) {
        Optional<Endereco> enderecoOptional = repository.findById(id);
        if(!enderecoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        Endereco endereco = enderecoOptional.get();
        if(endereco.getCliente() != null){
            Cliente cliente = endereco.getCliente();
            cliente.setEndereco(null);
            clienteRepository.save(cliente);
        }
        repository.deleteById(id);
        return ResponseEntity.ok().body(new CustomResponse<>(null, "Endereço excluído com sucesso"));
    }


    public ResponseEntity<CustomResponse<DtoEnderecoReponse>> enderecoPorId(Long id) {
        Optional<Endereco> enderecoOptional = repository.findById(id);
        if(!enderecoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        var endereco = enderecoOptional.get();
        return ResponseEntity.ok().body(new CustomResponse<>(new DtoEnderecoReponse(endereco), null));
    }
}
