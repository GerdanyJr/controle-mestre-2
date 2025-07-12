package com.github.gerdanyjr.controle_mestre_backend.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "quantidade_minima", nullable = false)
    private int quantidadeMnima;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;

    @ManyToOne
    private Categoria categoria;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "valor", length = 50, nullable = false)
    private BigDecimal valor;

    public Produto(
            Long codigo,
            String nome,
            int quantidadeMnima,
            String marca,
            Categoria categoria,
            int quantidade,
            BigDecimal valor
    ) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeMnima = quantidadeMnima;
        this.marca = marca;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Produto() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeMnima() {
        return quantidadeMnima;
    }

    public void setQuantidadeMnima(int quantidade_minima) {
        this.quantidadeMnima = quantidade_minima;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
