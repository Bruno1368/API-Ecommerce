package com.aplicacao.api.Specification;

import com.aplicacao.api.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Produto> nomeContains(String nome) {
        return (root, query, builder) ->
                nome == null ? builder.conjunction() : builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }


    public static Specification<Produto> precoGreaterThanEqual(Double precoMin){
        return(root, query, builder) ->
                precoMin == null ? builder.conjunction() : builder.greaterThanOrEqualTo(root.get("preco"), precoMin);

    }

    public static Specification<Produto> precoLessThanEqual(Double precoMax) {
        return (root, query, builder) ->
                precoMax == null ? builder.conjunction() : builder.lessThanOrEqualTo(root.get("preco"), precoMax);
    }


}
