package com.github.gerdanyjr.controle_mestre_backend.model.builder;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Funcionario;

public class FuncionarioBuilder {
    private Long id;
    private String cpf;
    private String email;
    private String telefone;
    private String nome;
    private String apelido;
    private Integer cargo;

    public Funcionario build() {
        return new Funcionario(
                id,
                cpf,
                email,
                telefone,
                nome,
                apelido,
                cargo
        );
    }

    public FuncionarioBuilder reset() {
        this.cpf = null;
        this.email = null;
        this.telefone = null;
        this.nome = null;
        this.apelido = null;
        this.cargo = null;
        return this;
    }

    public FuncionarioBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FuncionarioBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public FuncionarioBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public FuncionarioBuilder withTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public FuncionarioBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public FuncionarioBuilder withApelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public FuncionarioBuilder withCargo(Integer cargo) {
        this.cargo = cargo;
        return this;
    }

}

