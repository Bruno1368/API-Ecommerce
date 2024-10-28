package com.aplicacao.api.repository;

import com.aplicacao.api.model.Cliente;
import com.aplicacao.api.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findAll(Pageable page);
}
