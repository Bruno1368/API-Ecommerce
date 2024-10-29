package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoAlteraProduto;
import com.aplicacao.api.dto.DtoProduto;
import com.aplicacao.api.dto.DtoProdutoResponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping("cadastrar")
    @Transactional
    public ResponseEntity<CustomResponse> cadastrar(@Valid @RequestBody DtoProduto dtoProduto){
        return service.cadastrarProduto(dtoProduto);
    }

    @GetMapping
    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> todosProdutos(@PageableDefault(size = 10, sort = "nome")Pageable pageable){
        return service.todosProdutos(pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> atualizar(@PathVariable Long id, @RequestBody DtoAlteraProduto dtoAlteraProduto){
        return service.atualizaProduto(id, dtoAlteraProduto);
    }


}
