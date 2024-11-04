//package com.aplicacao.api.controller;
//
//import com.aplicacao.api.dto.DtoVenda;
//import com.aplicacao.api.dto.DtoVendaResponse;
//import com.aplicacao.api.model.Venda;
//import com.aplicacao.api.response.CustomResponse;
//import com.aplicacao.api.service.VendaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("vendas")
//public class VendaController {
//
//    @Autowired
//    VendaService service;
//
//    @PostMapping("cadastrar-venda")
//    public ResponseEntity<CustomResponse<DtoVendaResponse>> realizarVenda(@RequestBody DtoVenda venda){
//        return service.cadastrarVenda(venda);
//
//    }
//
//}
