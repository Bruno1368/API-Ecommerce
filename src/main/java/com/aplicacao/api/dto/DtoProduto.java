package com.aplicacao.api.dto;

import jakarta.validation.constraints.*;

public record DtoProduto(
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço tem que ser maior que zero")
        Double preco,
        @NotNull(message = "Estoque é obrigatório")
        @PositiveOrZero(message = "Estoque não pode ser negativo")
        Integer estoque,
        @NotBlank(message = "Código de produto obrigatório")
        @Size(max = 50, message = "O código do produto deve ter no máximo 50 caracteres")
        String codigo_produto) {

}
