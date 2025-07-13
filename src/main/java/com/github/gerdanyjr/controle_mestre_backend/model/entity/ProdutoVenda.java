package com.github.gerdanyjr.controle_mestre_backend.model.entity;

import com.github.gerdanyjr.controle_mestre_backend.model.embedded.ProdutoVendaId;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto_venda")
public class ProdutoVenda {

    @EmbeddedId
    private ProdutoVendaId id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    public ProdutoVenda() {
    }

    public ProdutoVenda(ProdutoVendaId id, Produto produto, Venda venda, BigDecimal valorUnitario, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.venda = venda;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ProdutoVendaId getId() {
        return id;
    }

    public void setId(ProdutoVendaId id) {
        this.id = id;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
