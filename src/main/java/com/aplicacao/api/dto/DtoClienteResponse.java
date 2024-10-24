package com.aplicacao.api.dto;

import com.aplicacao.api.model.Cliente;

public record DtoClienteResponse(String nome, String email) {

    public DtoClienteResponse(Cliente cliente){
        this(cliente.getNome(), cliente.getEmail());
    }
}
