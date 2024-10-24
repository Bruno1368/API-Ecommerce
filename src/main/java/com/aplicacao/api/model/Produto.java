package com.aplicacao.api.model;

import com.aplicacao.api.dto.DtoProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Produto")
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private int estoque;


    public Produto(DtoProduto produto) {
        this.id = produto.id();
        this.nome = produto.nome();
        this.preco = produto.preco();
        this.estoque = produto.quantidade();
    }


    @Override
    public String toString() {
        return this.nome;
    }
}
