package com.aplicacao.api.controller;

import com.aplicacao.api.dto.DtoCarrinho;
import com.aplicacao.api.dto.DtoCarrinhoResponse;
import com.aplicacao.api.response.CustomResponse;
import com.aplicacao.api.service.CarrinhoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService service;
    @PostMapping("/adicionar/{id}")
    @Transactional
    public ResponseEntity<CustomResponse<DtoCarrinhoResponse>> adicionarItens(@Valid @RequestBody DtoCarrinho dtoCarrinho, @PathVariable Long id){
        return service.adicionarItens(dtoCarrinho, id);
    }
}
