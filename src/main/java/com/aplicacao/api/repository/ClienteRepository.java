package com.aplicacao.api.repository;

import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    Boolean existsByEmail(String email);

    Page<Cliente> findAll(Pageable page);

    Page<Cliente> findAllByAtivoTrue(Pageable page);

    Page<Cliente> findAllByAtivoFalse(Pageable page);


    Optional<Cliente> findByEmail(String email);
}
