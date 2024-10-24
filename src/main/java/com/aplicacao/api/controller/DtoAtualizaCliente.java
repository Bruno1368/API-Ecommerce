package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoEndereco;

public record DtoAtualizaCliente(Long id, String nome, String email, DtoEndereco endereco) {
}
