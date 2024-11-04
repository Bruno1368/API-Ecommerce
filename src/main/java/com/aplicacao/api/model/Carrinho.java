package com.aplicacao.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Carrinho")
@Table(name = "carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho", fetch = FetchType.LAZY)
    private List<ItemCarrinho> itensCarrinho;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
