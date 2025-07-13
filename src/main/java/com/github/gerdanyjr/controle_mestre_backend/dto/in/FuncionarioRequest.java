package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "DTO para cadastro de funcionários")
public record FuncionarioRequest(
        @CPF(message = "Formato de CPF inválido!")
        @Schema(description = "CPF do funcionário", example = "12345678909")
        String cpf,

        @Email(message = "Formato de email inválido!")
        @Schema(description = "Email do funcionário", example = "joao.silva@email.com")
        String email,

        @NotBlank(message = "Número de telefone não pode ser vazio!")
        @Size(min = 11, max = 11, message = "Número de telefone deve conter 11 dígitos!")
        @Schema(description = "Número de telefone com DDD (somente dígitos)", example = "71991234567")
        String telefone,

        @NotBlank(message = "Nome não pode ser vazio!")
        @Schema(description = "Nome completo do funcionário", example = "João Silva")
        String nome,

        @NotBlank(message = "Apelido não pode ser vazio!")
        @Schema(description = "Apelido ou nome de identificação no sistema", example = "Joãozinho")
        String apelido,

        @NotNull(message = "Cargo não pode ser nulo!")
        @Schema(description = "Código do cargo atribuído ao funcionário", example = "1")
        Integer cargo

) {
}
