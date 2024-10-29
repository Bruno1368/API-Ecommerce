package com.aplicacao.api.service;

import com.aplicacao.api.dto.DtoAlteraProduto;
import com.aplicacao.api.dto.DtoEnderecoReponse;
import com.aplicacao.api.dto.DtoProduto;
import com.aplicacao.api.dto.DtoProdutoResponse;
import com.aplicacao.api.model.Produto;
import com.aplicacao.api.repository.ProdutoRepository;
import com.aplicacao.api.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;


    public ResponseEntity<CustomResponse> cadastrarProduto(DtoProduto dtoProduto) {
        Produto produto = Produto.toEntity(dtoProduto);
        if(!repository.existsByCodigoProduto(dtoProduto.codigo_produto())){
            repository.save(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse<>(new DtoProdutoResponse(produto), "Produto cadastrado com sucesso"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>(null, "Produto já cadastrado"));
    }

    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> todosProdutos(Pageable pageable) {
        Page<Produto> produtosPage = repository.findAll(pageable);
        var response = produtosPage.map(p -> new DtoProdutoResponse(p)).map(dtoProduto -> new CustomResponse<>(dtoProduto, null));
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<CustomResponse<DtoProdutoResponse>> atualizaProduto(Long id, DtoAlteraProduto dtoAlteraProduto) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if(!produtoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        Produto produto = produtoOptional.get();
        produto.atualizaDados(dtoAlteraProduto);
        repository.save(produto);
        return ResponseEntity.ok().body(new CustomResponse<>(new DtoProdutoResponse(produto), null));
    }
}
