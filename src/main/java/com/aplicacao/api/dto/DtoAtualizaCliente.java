package com.aplicacao.api.dto;

import com.aplicacao.api.dto.DtoEndereco;

public record DtoAtualizaCliente(String nome, String email, DtoAtualizaEndereco endereco) {
}
