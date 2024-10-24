package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoProduto;
import com.aplicacao.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping("cadastrar")
    public void cadastrar(@RequestBody DtoProduto produto){
        service.cadastrarProduto(produto);
    }

}
