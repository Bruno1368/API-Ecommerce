package com.aplicacao.api.dto;

import com.aplicacao.api.dto.DtoEndereco;

public record DtoAtualizaCliente(Long id, String nome, String email, DtoEndereco endereco) {
}
