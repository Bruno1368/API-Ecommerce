package com.aplicacao.api.dto.enderecoDTO;
import com.aplicacao.api.model.UF;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record DtoEndereco(
        Long id,
        @NotBlank(message = "Nome da rua é obrigatório")
        String nomeRua,
        @NotNull(message = "Número da casa é obrigatório")
        Integer numeroCasa,
        @NotBlank(message = "Nome da cidade é obrigatório")
        String cidade,
        @NotNull(message = "UF é obrigatório")
        UF uf){
}
