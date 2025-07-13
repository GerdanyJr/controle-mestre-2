package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClienteResponse(
        @Schema(description = "Id do cliente", example = "1")
        Long id,

        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,

        @Schema(description = "Código do sexo (1 = Feminino, 2 = Masculino, 3 = Não especificado)", example = "2")
        Integer sexo,

        @Schema(description = "Data de nascimento no formato ISO", example = "1990-05-20")
        LocalDate dataNascimento
) {

}
