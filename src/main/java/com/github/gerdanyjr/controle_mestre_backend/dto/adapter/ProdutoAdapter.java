package com.github.gerdanyjr.controle_mestre_backend.dto.adapter;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.builder.ProdutoBuilder;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAdapter {
    public Produto toEntity(ProdutoRequest request, Categoria categoria) {
        return new ProdutoBuilder()
                .withNome(request.nome())
                .withMarca(request.marca())
                .withCategoria(categoria)
                .withQuantidade(request.quantidade())
                .withQuantidadeMinima(request.quantidadeMinima())
                .withValor(request.valor())
                .build();
    }

    public ProdutoResponse toResponse(Produto entity) {
        return ProdutoResponse
                .builder()
                .codigo(entity.getCodigo())
                .quantidade(entity.getQuantidade())
                .marca(entity.getMarca())
                .categoriaId(entity.getCategoria().getId())
                .valor(entity.getValor())
                .nome(entity.getNome())
                .build();
    }
}
