package com.aplicacao.api.dto.clienteDTO;

import com.aplicacao.api.dto.enderecoDTO.DtoAtualizaEndereco;

public record DtoAtualizaCliente(String nome, String email, DtoAtualizaEndereco endereco) {
}
