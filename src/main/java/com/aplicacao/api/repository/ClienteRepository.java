package com.aplicacao.api.repository;

import com.aplicacao.api.dto.DtoClienteResponse;
import com.aplicacao.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Boolean existsByEmail(String email);

    List<Cliente> findAllByAtivoTrue();
}
