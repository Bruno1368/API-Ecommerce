package com.aplicacao.api.dto;

import java.util.List;

public record DtoCarrinho(Long id, List<DtoItemCarrinho> itemCarrinho) {
}
