package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoResponse(
        @Schema(description = "Codigo do produto", example = "12345678") Long codigo,
        @Schema(description = "Nome do produto", example = "Perfume") String nome,
        @Schema(description = "Quantidade mínima do produto", example = "1") int quantidadeMinima,
        @Schema(description = "Marca do produto", example = "Avon") String marca,
        @Schema(description = "Id da categoria do produto", example = "1") Long categoriaId,
        @Schema(description = "Quantidade do produto", example = "1") int quantidade,
        @Schema(description = "Valor do produto", example = "10.50") BigDecimal valor
) {
}
