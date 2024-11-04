package com.aplicacao.api.dto.enderecoDTO;

import com.aplicacao.api.model.UF;

public record DtoAtualizaEndereco(String nomeRua, Integer numeroCasa, String cidade, UF uf) {
}
