package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoAlteraProduto;
import com.aplicacao.api.dto.DtoNovoEstoque;
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

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<CustomResponse> cadastrar(@Valid @RequestBody DtoProduto dtoProduto){
        return service.cadastrarProduto(dtoProduto);
    }

    @GetMapping
    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> todosProdutos(@PageableDefault(size = 10, sort = "nome")Pageable pageable){
        return service.todosProdutos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> produtoPorId(@PathVariable Long id){
        return service.produtoPorId(id);
    }

    @GetMapping("/codigo/{codigoProduto}")
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> produtoPorCodigo(@PathVariable String codigoProduto){
        return service.produtoPorCodigo(codigoProduto);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> produtosAtivos(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return service.produtosAtivos(pageable);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> produtosInativos(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return service.produtosInativos(pageable);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> buscarProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
            return service.buscarProdutos(nome, precoMin, precoMax, pageable);
    }

    @PutMapping("/{id}/estoque")
    @Transactional
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> atualizaEstoque(@PathVariable Long id, @RequestBody DtoNovoEstoque dtoNovoEstoque){
        return service.atualizaEstoque(id, dtoNovoEstoque);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> atualizar(@PathVariable Long id, @RequestBody DtoAlteraProduto dtoAlteraProduto){
        return service.atualizaProduto(id, dtoAlteraProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<DtoProdutoResponse>> deletar(@PathVariable Long id){
        return service.deletaProduto(id);
    }


}
