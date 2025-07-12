package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome não pode estar vazio") String nome,
        @PositiveOrZero(message = "A quantidade mínima não pode ser negativa") int quantidadeMinima,
        @NotBlank(message = "A marca não pode estar vazio") String marca,
        @NotNull(message = "Categoria não pode ser nula")  Categoria categoria,
        @PositiveOrZero(message = "A quantidade do produto não pode ser negativa")  int quantidade,
        @Positive(message = "O valor de um produto não pode ser vazio") BigDecimal valor
) {
}
