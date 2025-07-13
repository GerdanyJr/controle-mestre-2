package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FuncionarioResponse(
        @Schema(description = "Id do funcionário", example = "1")
        Long id,

        @Schema(description = "Email do funcionário", example = "joao.silva@email.com")
        String email,

        @Schema(description = "Número de telefone com DDD (somente dígitos)", example = "71991234567")
        String telefone,

        @Schema(description = "Nome completo do funcionário", example = "João Silva")
        String nome,

        @Schema(description = "Apelido ou nome de identificação no sistema", example = "Joãozinho")
        String apelido,

        @Schema(description = "Código do cargo atribuído ao funcionário", example = "1")
        Integer cargo

) {


}
