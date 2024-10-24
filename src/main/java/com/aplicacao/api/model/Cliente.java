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

    public static Cliente atualziaCliente(DtoAtualizaCliente atualizaCliente){
        Cliente cliente = new Cliente();
        if(atualizaCliente.nome() != null){
            cliente.setNome(atualizaCliente.nome());
        }
        if(atualizaCliente.email() != null){
            cliente.setEmail(atualizaCliente.email());
        }
        if(atualizaCliente.endereco() != null){
            if(atualizaCliente.endereco().cidade() != null){
                cliente.endereco.setCidade(atualizaCliente.endereco().cidade());
            }
            if(atualizaCliente.endereco().nomeRua() != null){
                cliente.endereco.setNomeRua(atualizaCliente.endereco().nomeRua());
            }
            if(atualizaCliente.endereco().uf() != null){
                cliente.endereco.setUf(atualizaCliente.endereco().uf());
            }
        }
    }
}
