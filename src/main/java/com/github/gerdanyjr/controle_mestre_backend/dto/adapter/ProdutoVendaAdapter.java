package com.github.gerdanyjr.controle_mestre_backend.dto.adapter;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoVendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoVendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.builder.ProdutoVendaBuilder;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import org.springframework.stereotype.Component;

@Component
public class ProdutoVendaAdapter {

    public ProdutoVenda toEntity(ProdutoVendaRequest request, Produto produto) {
        return new ProdutoVendaBuilder()
                .withProduto(produto)
                .withValorUnitario(produto.getValor())
                .withQuantidade(request.quantidade())
                .build();
    }

    public ProdutoVendaResponse toResponse(ProdutoVenda entity) {
        return ProdutoVendaResponse
                .builder()
                .id(entity.getId())
                .quantidade(entity.getQuantidade())
                .valorUnitario(entity.getValorUnitario())
                .build();
    }
}
