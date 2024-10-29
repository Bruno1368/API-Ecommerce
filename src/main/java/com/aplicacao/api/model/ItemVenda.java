package com.aplicacao.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ItemVenda")
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "venda")
    private Venda venda;
    private Integer quantidade;
    private Double valorUnitario;


}
