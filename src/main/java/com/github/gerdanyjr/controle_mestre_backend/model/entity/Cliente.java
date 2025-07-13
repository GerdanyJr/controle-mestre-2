package com.github.gerdanyjr.controle_mestre_backend.model.entity;

import com.github.gerdanyjr.controle_mestre_backend.model.converter.SexoEnumConverter;
import com.github.gerdanyjr.controle_mestre_backend.model.enums.SexoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Convert(converter = SexoEnumConverter.class)
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    public Cliente() {
    }

    public Cliente(Long id, String cpf, String nome, SexoEnum sexo, LocalDate dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
