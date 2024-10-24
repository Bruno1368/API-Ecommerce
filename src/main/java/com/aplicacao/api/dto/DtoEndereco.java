package com.aplicacao.api.dto;
import com.aplicacao.api.model.UF;
import jakarta.validation.constraints.NotNull;
public record DtoEndereco(
        Long id,
        @NotNull(message = "Nome da rua é obrigatório")
        String nomeRua,
        @NotNull(message = "Número da casa é obrigatório")
        Integer numeroCasa,
        @NotNull(message = "Nome da cidade é obrigatório")
        String cidade,
        @NotNull(message = "UF é obrigatório")
        UF uf){
}
