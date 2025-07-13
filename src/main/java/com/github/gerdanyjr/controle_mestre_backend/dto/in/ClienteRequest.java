package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Schema(description = "DTO para cadastro de cliente")
public record ClienteRequest(
        @NotBlank(message = "CPF não pode ser vazio!")
        @Schema(description = "CPF do cliente (somente dígitos)", example = "12345678909")
        String cpf,

        @NotBlank(message = "Nome não pode ser vazio!")
        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,

        @NotNull(message = "Sexo não pode ser nulo!")
        @Schema(description = "Código do sexo (1 = Feminino, 2 = Masculino, 3 = Não especificado)", example = "2")
        Integer sexo,

        @NotNull(message = "Data de nascimento não pode ser nula!")
        @Past(message = "A data de nascimento deve estar no passado!")
        @Schema(description = "Data de nascimento no formato ISO", example = "1990-05-20")
        LocalDate dataNascimento
) {
}