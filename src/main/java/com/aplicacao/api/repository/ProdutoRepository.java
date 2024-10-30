package com.aplicacao.api.repository;

import com.aplicacao.api.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {
    Boolean existsByCodigoProduto(String codigoProduto);

    Optional<Produto> findByCodigoProduto(String codigoProduto);

    Page<Produto> findAllByAtivoTrue(Pageable pageable);

    Page<Produto> findAllByAtivoFalse(Pageable pageable);

    Page<Produto> findByNome(String nome, Pageable pageable);

    Page<Produto> findAllByPrecoGreaterThanEqual(Double precoMin, Pageable pageable);

    Page<Produto> findAllByPrecoLessThanEqual(Double precoMax, Pageable pageable);
}
