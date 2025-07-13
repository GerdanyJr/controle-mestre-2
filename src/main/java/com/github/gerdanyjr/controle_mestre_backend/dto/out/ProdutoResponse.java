package com.github.gerdanyjr.controle_mestre_backend.dto.out;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoResponse(
        Long codigo,
        String nome,
        int quantidadeMnima,
        String marca,
        Long categoriaId,
        int quantidade,
        BigDecimal valor
) {
}
