package com.aplicacao.api.model;

import com.aplicacao.api.dto.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
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
@Entity(name = "Cliente")
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private Boolean ativo = true;

    public static Cliente toEntity(DtoCliente dtoCliente){
        Cliente cliente = new Cliente();
        cliente.setId(dtoCliente.id());
        cliente.setNome(NormalizaString.primeiraLetraMaiusculo(dtoCliente.nome()));
        cliente.setEmail(dtoCliente.email().toLowerCase());
        if(dtoCliente.endereco() != null){
            cliente.setEndereco(Endereco.toEntity(dtoCliente.endereco()));
        }
        return cliente;
    }

    public void atualizaDados(DtoAtualizaCliente atualizaCliente){
        if(atualizaCliente.nome() != null){
            this.nome = NormalizaString.primeiraLetraMaiusculo(atualizaCliente.nome());
        }
        if(atualizaCliente.email() != null){
            this.email = atualizaCliente.email().toLowerCase();
        }
        if(atualizaCliente.endereco() != null){
            endereco.atualizaDados(atualizaCliente.endereco());
        }
    }

    public void excluirCliente() {
        setAtivo(false);
    }
}
