package com.aplicacao.api.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record DtoNovoEstoque(
        @PositiveOrZero(message = "Estoque não pode ser negativo")
        Integer estoque) {
}
