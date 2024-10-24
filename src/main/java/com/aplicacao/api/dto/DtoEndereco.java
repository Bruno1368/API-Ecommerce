package com.aplicacao.api.dto;

import com.aplicacao.api.model.UF;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.message.Message;

public record DtoEndereco(
        Long id,
        @NotNull(message = "Nome da rua é obrigatório")
        String nomeRua,
        @NotNull(message = "Número da casa é obrigatório")
        int numeroCasa,
        @NotNull(message = "Nome da cidade é obrigatório")
        String cidade,
        @NotNull(message = "UF é obrigatório")
        UF uf,
        @NotNull(message = "Adicione um cliente ao endereço")
        Long idCliente) {
}
