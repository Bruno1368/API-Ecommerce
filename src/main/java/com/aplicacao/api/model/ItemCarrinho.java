package com.aplicacao.api.model;

import com.aplicacao.api.dto.DtoItemCarrinho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ItemCarrinho")
@Table(name = "itens_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;
    private Integer quantidade;
    private Double valorUnitario;



}
