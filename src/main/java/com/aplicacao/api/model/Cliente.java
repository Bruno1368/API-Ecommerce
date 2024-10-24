package com.aplicacao.api.model;

import com.aplicacao.api.controller.DtoAtualizaCliente;
import com.aplicacao.api.dto.DtoCliente;
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
        cliente.setNome(dtoCliente.nome());
        cliente.setEmail(dtoCliente.email());
        if(dtoCliente.endereco() != null){
            cliente.setEndereco(Endereco.toEntity(dtoCliente.endereco()));
        }
        return cliente;
    }

    public void atualizaDados(DtoAtualizaCliente atualizaCliente){
        if(atualizaCliente.nome() != null){
            this.nome = atualizaCliente.nome();
        }
        if(atualizaCliente.email() != null){
            this.email = atualizaCliente.email();
        }
        if(atualizaCliente.endereco() != null){
            endereco.atualizaDados(atualizaCliente.endereco());
        }
    }
}
