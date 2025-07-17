package com.github.gerdanyjr.controle_mestre_backend.dto.adapter;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.VendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoVendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.VendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.builder.VendaBuilder;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class VendaAdapter {

    private final ProdutoVendaAdapter produtoVendaAdapter;

    public VendaAdapter(ProdutoVendaAdapter produtoVendaAdapter) {
        this.produtoVendaAdapter = produtoVendaAdapter;
    }

    public Venda toEntity(VendaRequest vendaRequest, BigDecimal total, List<ProdutoVenda> produtoVendas) {
        return new VendaBuilder()
                .withData(vendaRequest.data())
                .withTotal(total)
                .withProdutoVendas(produtoVendas)
                .build();
    }

    public VendaResponse toResponse(Venda venda) {
        List<ProdutoVendaResponse> produtosVenda = venda.getProdutos()
                .stream()
                .map(produtoVendaAdapter::toResponse)
                .toList();

        return VendaResponse
                .builder()
                .id(venda.getId())
                .total(venda.getTotal())
                .funcionarioId(venda.getFuncionario().getId())
                .clienteId(venda.getCliente().getId())
                .produtos(produtosVenda)
                .build();
    }

}
