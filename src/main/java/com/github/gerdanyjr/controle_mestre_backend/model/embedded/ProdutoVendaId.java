package com.github.gerdanyjr.controle_mestre_backend.model.embedded;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Embeddable
public class ProdutoVendaId {

    @ManyToOne
    @MapsId("id_venda")
    private Venda venda;

    @ManyToOne
    @MapsId("id_produto")
    private Produto produto;

    public ProdutoVendaId() {
    }

    public ProdutoVendaId(Venda venda, Produto produto) {
        this.venda = venda;
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
