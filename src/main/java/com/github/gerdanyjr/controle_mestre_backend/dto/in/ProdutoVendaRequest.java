package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO para cadastro de venda de produto")
public record ProdutoVendaRequest(
        @NotNull(message = "Id não pode ser nulo")
        @Schema(description = "Id do produto", example = "1")
        Long id,

        @Positive(message = "Quantidade não pode ser 0 ou negativa")
        @Schema(description = "Quantidade vendida", example = "1")
        int quantidade
) {

}
