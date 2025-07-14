package com.github.gerdanyjr.controle_mestre_backend.model.builder;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;

import java.math.BigDecimal;

public class ProdutoVendaBuilder {
    private Long id;
    private Produto produto;
    private Venda venda;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public ProdutoVenda build() {
        return new ProdutoVenda(
                id,
                produto,
                valorUnitario,
                quantidade
        );
    }

    public ProdutoVendaBuilder reset() {
        this.produto = null;
        this.venda = null;
        this.valorUnitario = null;
        this.quantidade = null;
        return this;
    }

    public ProdutoVendaBuilder withProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ProdutoVendaBuilder withVenda(Venda venda) {
        this.venda = venda;
        return this;
    }

    public ProdutoVendaBuilder withValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        return this;
    }

    public ProdutoVendaBuilder withQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

}

