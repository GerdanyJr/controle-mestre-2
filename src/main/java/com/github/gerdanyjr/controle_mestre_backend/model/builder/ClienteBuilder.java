package com.github.gerdanyjr.controle_mestre_backend.model.builder;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Cliente;
import com.github.gerdanyjr.controle_mestre_backend.model.enums.SexoEnum;

import java.time.LocalDate;

public class ClienteBuilder {
    private Long id;
    private String cpf;
    private String nome;
    private SexoEnum sexo;
    private LocalDate dataNascimento;

    public Cliente build() {
        return new Cliente(id, cpf, nome, sexo, dataNascimento);
    }

    public ClienteBuilder reset() {
        this.id = null;
        this.cpf = null;
        this.nome = null;
        this.sexo = null;
        this.dataNascimento = null;
        return this;
    }

    public ClienteBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ClienteBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder withSexo(SexoEnum sexo) {
        this.sexo = sexo;
        return this;
    }

    public ClienteBuilder withDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

}
