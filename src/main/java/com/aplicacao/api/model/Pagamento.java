package com.aplicacao.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Pagamento")
@Table(name = "Pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    private Venda venda;
    private Status status;
    private MetodoPagamento metodo_pagamento;
    private BigDecimal valor;

}
