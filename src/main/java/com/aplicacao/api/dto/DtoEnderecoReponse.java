package com.aplicacao.api.dto;

import com.aplicacao.api.model.UF;

public record DtoEnderecoReponse(String nomeRua, Integer numeroCasa, String cidade, UF uf) {
}
