package com.aplicacao.api.model;

import com.aplicacao.api.dto.DtoEndereco;
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
    private String nomeRua;
    private int numeroCasa;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private UF uf;
    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;

    public static Endereco toEntity(DtoEndereco dtoEndereco){
        Endereco endereco = new Endereco();
        endereco.setId(dtoEndereco.id());
        endereco.setNomeRua(dtoEndereco.nomeRua());
        endereco.setNumeroCasa(dtoEndereco.numeroCasa());
        endereco.setCidade(dtoEndereco.cidade());
        endereco.setUf(dtoEndereco.uf());
        return endereco;
    }
}
