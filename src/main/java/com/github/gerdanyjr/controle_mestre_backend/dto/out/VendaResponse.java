package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record VendaResponse(
        @Schema(description = "Id do produto", example = "1")
        Long id,

        @Schema(description = "Valor total em R$ da venda", example = "22.60")
        BigDecimal total,

        @Schema(description = "Id do cliente que comprou", example = "1")
        Long clienteId,

        @Schema(description = "Id do funcionario que realizou comprou", example = "1")
        Long funcionarioId,

        @Schema(description = "Produtos vendidos", example = "[{\"id\": 1, \"quantidade\": 2}, {\"id\": 2, \"quantidade\": 1}]")
        List<ProdutoVendaResponse> produtos
) {
}
