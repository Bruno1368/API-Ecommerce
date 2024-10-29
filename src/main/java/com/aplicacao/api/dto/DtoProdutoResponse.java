package com.aplicacao.api.dto;

import com.aplicacao.api.model.Produto;

public record DtoProdutoResponse(String nome, Double preco, Integer estoque) {
    public DtoProdutoResponse(Produto produto){
        this(produto.getNome(), produto.getPreco(), produto.getEstoque());
    }
}