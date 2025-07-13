package com.github.gerdanyjr.controle_mestre_backend.model.embedded;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.util.Objects;

@Embeddable
public class ProdutoVendaId {

    @Column(name = "venda_id")
    private Long vendaId;

    @Column(name = "produto_id")
    private Long produtoId;

    public ProdutoVendaId() {
    }

    public ProdutoVendaId(Long vendaId, Long produtoId) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
    }

    public Long getVendaId() {
        return vendaId;
    }

    public void setVendaId(Long vendaId) {
        this.vendaId = vendaId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoVendaId that = (ProdutoVendaId) o;
        return Objects.equals(vendaId, that.vendaId) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, produtoId);
    }
}
