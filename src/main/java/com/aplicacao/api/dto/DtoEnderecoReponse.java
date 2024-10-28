package com.aplicacao.api.dto;

import com.aplicacao.api.model.Endereco;
import com.aplicacao.api.model.UF;

public record DtoEnderecoReponse(String nomeRua, Integer numeroCasa, String cidade, UF uf) {

    public DtoEnderecoReponse(Endereco endereco){
        this(endereco.getNomeRua(), endereco.getNumeroCasa(), endereco.getCidade(), endereco.getUf());
    }
}
