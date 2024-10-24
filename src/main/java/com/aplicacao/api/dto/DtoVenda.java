package com.aplicacao.api.dto;
import java.util.List;

public record DtoVenda(Long id, List<DtoItemVenda> itens) {

}
