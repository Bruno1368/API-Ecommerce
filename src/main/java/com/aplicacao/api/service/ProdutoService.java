package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoProduto;
import com.aplicacao.api.model.Produto;
import com.aplicacao.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public void cadastrarProduto(DtoProduto produto){
        repository.save(new Produto(produto));
    }
}
