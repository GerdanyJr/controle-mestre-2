package com.github.gerdanyjr.controle_mestre_backend.model.builder;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VendaBuilder {
    private Long id;
    private LocalDate data;
    private List<ProdutoVenda> produtoVendas;
    private BigDecimal total;

    public Venda build() {
        return new Venda(id, data, produtoVendas, total);
    }

    public VendaBuilder reset() {
        this.id = null;
        this.data = null;
        this.produtoVendas = null;
        this.total = null;
        return this;
    }

    public VendaBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public VendaBuilder withData(LocalDate data) {
        this.data = data;
        return this;
    }

    public VendaBuilder withProdutoVendas(List<ProdutoVenda> produtoVendas) {
        this.produtoVendas = produtoVendas;
        return this;
    }

    public VendaBuilder withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

}
