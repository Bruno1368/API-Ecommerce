package com.aplicacao.api.model;

import com.aplicacao.api.dto.enderecoDTO.DtoAtualizaEndereco;
import com.aplicacao.api.dto.enderecoDTO.DtoEndereco;
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
@Entity(name = "Endereco")
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_rua")
    private String nomeRua;
    @Column(name = "numero_casa")
    private Integer numeroCasa;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private UF uf;
    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;

    public static Endereco toEntity(DtoEndereco dtoEndereco){
        Endereco endereco = new Endereco();
        endereco.setId(dtoEndereco.id());
        endereco.setNomeRua(NormalizaString.primeiraLetraMaiusculo(dtoEndereco.nomeRua()));
        endereco.setNumeroCasa(dtoEndereco.numeroCasa());
        endereco.setCidade(NormalizaString.primeiraLetraMaiusculo(dtoEndereco.cidade()));
        endereco.setUf(dtoEndereco.uf());
        return endereco;
    }

    public void atualizaDados(DtoAtualizaEndereco endereco) {
        if(endereco.nomeRua() != null){
            this.nomeRua = NormalizaString.primeiraLetraMaiusculo(endereco.nomeRua());
        }
        if(endereco.numeroCasa() != null){
            this.numeroCasa = endereco.numeroCasa();
        }
        if(endereco.cidade() != null){
            this.cidade = NormalizaString.primeiraLetraMaiusculo(endereco.cidade());
        }
        if(endereco.uf() != null){
            this.uf = endereco.uf();
        }
    }
}
