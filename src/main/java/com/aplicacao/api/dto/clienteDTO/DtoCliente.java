package com.aplicacao.api.dto.clienteDTO;
import com.aplicacao.api.dto.enderecoDTO.DtoEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoCliente(
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Email é obrigatório")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,7}$", message = "E-mail inválido")
        String email,
        DtoEndereco endereco) {
}
