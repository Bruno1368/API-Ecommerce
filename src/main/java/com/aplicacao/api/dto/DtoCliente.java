package com.aplicacao.api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record DtoCliente(
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Email é obrigatório")
        String email,
        DtoEndereco endereco) {
}
