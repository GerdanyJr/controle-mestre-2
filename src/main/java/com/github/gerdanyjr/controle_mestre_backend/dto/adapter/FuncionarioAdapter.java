package com.github.gerdanyjr.controle_mestre_backend.dto.adapter;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.FuncionarioRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.FuncionarioResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.builder.FuncionarioBuilder;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioAdapter {

    public Funcionario toEntity(FuncionarioRequest funcionarioRequest) {
        return new FuncionarioBuilder()
                .withCpf(funcionarioRequest.cpf())
                .withEmail(funcionarioRequest.email())
                .withTelefone(funcionarioRequest.telefone())
                .withNome(funcionarioRequest.nome())
                .withApelido(funcionarioRequest.apelido())
                .withCargo(funcionarioRequest.cargo())
                .build();
    }

    public FuncionarioResponse toResponse(Funcionario funcionario) {
        return FuncionarioResponse
                .builder()
                .id(funcionario.getId())
                .email(funcionario.getEmail())
                .telefone(funcionario.getTelefone())
                .nome(funcionario.getNome())
                .apelido(funcionario.getApelido())
                .cargo(funcionario.getCargo())
                .build();
    }

}
