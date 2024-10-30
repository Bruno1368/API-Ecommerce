package com.aplicacao.api.model;

import com.aplicacao.api.dto.DtoAlteraProduto;
import com.aplicacao.api.dto.DtoNovoEstoque;
import com.aplicacao.api.dto.DtoProduto;
import com.aplicacao.api.validator.NormalizaString;
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
    private Integer estoque;
    @Column(name = "codigo_produto")
    private String codigoProduto;
    private Boolean ativo = true;


    public static Produto toEntity(DtoProduto dtoProduto){
        Produto produto = new Produto();
        produto.setId(dtoProduto.id());
        produto.setNome(NormalizaString.primeiraLetraMaiusculo(dtoProduto.nome()));
        produto.setPreco(dtoProduto.preco());
        produto.setEstoque(dtoProduto.estoque());
        produto.setCodigoProduto(dtoProduto.codigo_produto().toUpperCase());
        return produto;
    }

    public void atualizaDados(DtoAlteraProduto dtoAlteraProduto) {
        if(dtoAlteraProduto.nome() != null){
            this.nome = NormalizaString.primeiraLetraMaiusculo(dtoAlteraProduto.nome());
        }
        if(dtoAlteraProduto.preco() != null){
            this.preco = dtoAlteraProduto.preco();
        }
        if(dtoAlteraProduto.estoque() != null){
            this.estoque = dtoAlteraProduto.estoque();
        }
        if(dtoAlteraProduto.codigo_produto() != null){
            this.codigoProduto = dtoAlteraProduto.codigo_produto().toUpperCase();
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public void atualizarEstoque(DtoNovoEstoque novoEstoque) {
        this.setEstoque(novoEstoque.estoque());
    }

    public void desativar() {
         setAtivo(false);
    }
}
