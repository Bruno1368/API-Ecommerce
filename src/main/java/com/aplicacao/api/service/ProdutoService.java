package com.aplicacao.api.service;

import com.aplicacao.api.Specification.ProductSpecification;
import com.aplicacao.api.dto.*;
import com.aplicacao.api.model.Produto;
import com.aplicacao.api.repository.ProdutoRepository;
import com.aplicacao.api.response.CustomResponse;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return ResponseEntity.ok().body(new CustomResponse<>(new DtoProdutoResponse(produto), "Produto atualizado"));
    }

    public ResponseEntity<CustomResponse<DtoProdutoResponse>> desativaProduto(Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if(!produtoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        var produto = produtoOptional.get();
        produto.desativar();
        repository.save(produto);
        
        return ResponseEntity.ok().body(new CustomResponse<>(null, "Produto desativado"));
    }

    public ResponseEntity<CustomResponse<DtoProdutoResponse>> produtoPorId(Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if(!produtoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        var response = new DtoProdutoResponse(produtoOptional.get());

        return ResponseEntity.ok().body(new CustomResponse<>(response, null));

    }

    public ResponseEntity<CustomResponse<DtoProdutoResponse>> atualizaEstoque(Long id, DtoNovoEstoque novoEstoque ) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if(!produtoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        var produto = produtoOptional.get();
        produto.atualizarEstoque(novoEstoque);

        return ResponseEntity.ok().body(new CustomResponse<>(new DtoProdutoResponse(produto), "Estoque atualizado"));
    }

    public ResponseEntity<CustomResponse<DtoProdutoResponse>> produtoPorCodigo(String codigoProduto) {
        Optional<Produto> produtoOptional = repository.findByCodigoProduto(codigoProduto);
        if(!produtoOptional.isPresent()){
            throw new NoSuchElementException();
        }
        var response = new DtoProdutoResponse(produtoOptional.get());
        return ResponseEntity.ok().body(new CustomResponse<>(response, null));
    }

    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> produtosAtivos(Pageable pageable) {
       Page<Produto> produtosPage = repository.findAllByAtivoTrue(pageable);
       var response = produtosPage.map(DtoProdutoResponse::new).map(dtoProdutoResponse -> new CustomResponse<>(dtoProdutoResponse, null));
       
       return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> produtosInativos(Pageable pageable) {
      Page<Produto> produtoPage = repository.findAllByAtivoFalse(pageable);
      var response = produtoPage.map(DtoProdutoResponse::new).map(dtoProduto -> new CustomResponse<>(dtoProduto, null));
      return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> buscarProdutos(String nome, Double precoMin, Double precoMax, Pageable pageable) {
        Specification<Produto> spec = Specification.where(ProductSpecification.nomeContains(nome))
                .and(ProductSpecification.precoGreaterThanEqual(precoMin))
                .and(ProductSpecification.precoLessThanEqual(precoMax));

        Page<Produto> produtoPage = repository.findAll(spec, pageable);
        var response = produtoPage.map(DtoProdutoResponse::new).map(dto -> new CustomResponse<>(dto, null));

        return ResponseEntity.ok().body(response);

    }

    public ResponseEntity<Page<CustomResponse<DtoProdutoResponse>>> produtosOrdenados(String sort, Boolean asc, Boolean desc,Pageable pageable) {

        Sort sortOrder = null;

        if(Boolean.TRUE.equals(asc) && Boolean.TRUE.equals(desc)){
            throw new IllegalArgumentException("Escolha apenas uma direção de ordenação: asc ou desc");
        }else if(Boolean.TRUE.equals(asc)){
            sortOrder = Sort.by(Sort.Direction.ASC, sort);
        }else if(Boolean.TRUE.equals(desc)){
            sortOrder = Sort.by(Sort.Direction.DESC, sort);
        }else{
            sortOrder = Sort.by(sort);
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);
        Page<Produto> produtoPage = repository.findAll(sortedPageable);
        var response = produtoPage.map(DtoProdutoResponse::new).map(dtoProduto -> new CustomResponse<>(dtoProduto, null));

        return ResponseEntity.ok().body(response);
    }
}
