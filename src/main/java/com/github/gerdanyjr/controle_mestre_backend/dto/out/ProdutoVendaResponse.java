package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(description = "DTO para retornar informações de um produto vendido")
public record ProdutoVendaResponse(
        @Schema(description = "Id do produto", example = "1")
        Long id,

        @Schema(description = "Quantidade vendida", example = "1")
        int quantidade,

        @Schema(description = "Valor unitário", example = "13.50")
        BigDecimal valorUnitario
) {

}
