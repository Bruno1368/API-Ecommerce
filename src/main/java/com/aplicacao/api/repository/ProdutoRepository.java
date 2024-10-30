package com.aplicacao.api.repository;

import com.aplicacao.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Boolean existsByCodigoProduto(String codigoProduto);

    Optional<Produto> findByCodigoProduto(String codigoProduto);
}
