package com.aplicacao.api.Specification;

import com.aplicacao.api.model.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    public static Specification<Cliente> nomeContains(String nome){
        return(root, query, builder) ->
                nome == null ? builder.conjunction() : builder.like(builder.lower(root.get("nome")),"%" + nome.toLowerCase() + "%");
    }

    public static Specification<Cliente> enderecoCidadeContains(String cidade){
        return(root, query, builder) ->
                cidade == null ? builder.conjunction() : builder.like(builder.lower(root.join("endereco").get("cidade")), "%" + cidade.toLowerCase() + "%");
    }

    public static Specification<Cliente> byAtivo(Boolean booleano){
        return(root, query, builder) ->
                booleano == null ? builder.conjunction() : builder.equal(root.get("ativo"), booleano);
    }

}
