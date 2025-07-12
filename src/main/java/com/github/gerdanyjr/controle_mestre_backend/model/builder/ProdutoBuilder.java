package com.github.gerdanyjr.controle_mestre_backend.model.builder;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;

import java.math.BigDecimal;

public class ProdutoBuilder {
    private Long codigo;
    private String nome;
    private Integer quantidadeMinima;
    private String marca;
    private Categoria categoria;
    private Integer quantidade;
    private BigDecimal valor;

    public Produto build() {
        return new Produto(
                codigo,
                nome,
                quantidadeMinima,
                marca,
                categoria,
                quantidade,
                valor
        );
    }

    public void reset() {
        this.codigo = null;
        this.nome = null;
        this.quantidadeMinima = null;
        this.marca = null;
        this.categoria = null;
        this.quantidade = null;
        this.valor = null;
    }

    public ProdutoBuilder withCodigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public ProdutoBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoBuilder withQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
        return this;
    }

    public ProdutoBuilder withMarca(String marca) {
        this.marca = marca;
        return this;
    }

    public ProdutoBuilder withCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdutoBuilder withQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ProdutoBuilder withValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}
