package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(description = "DTO para cadastro de produto")
public record ProdutoRequest(
        @NotBlank(message = "O nome não pode estar vazio")
        @Schema(description = "Nome do produto", example = "Perfume")
        String nome,
        @PositiveOrZero(message = "A quantidade mínima não pode ser negativa")
        @Schema(description = "Quantidade mínima do produto", example = "1")
        int quantidadeMinima,
        @NotBlank(message = "A marca não pode estar vazio")
        @Schema(description = "Marca do produto", example = "Avon")
        String marca,
        @NotNull(message = "Categoria não pode ser nula")
        @Schema(description = "Id da categoria do produto", example = "1")
        Long categoriaId,
        @PositiveOrZero(message = "A quantidade do produto não pode ser negativa")
        @Schema(description = "Quantidade de estoque produto", example = "1")
        int quantidade,
        @Positive(message = "O valor de um produto não pode ser vazio")
        @Schema(description = "Valor do produto", example = "10.50")
        BigDecimal valor
) {
}
