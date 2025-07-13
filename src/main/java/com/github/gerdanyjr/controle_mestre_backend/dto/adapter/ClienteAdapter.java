package com.github.gerdanyjr.controle_mestre_backend.dto.adapter;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ClienteRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ClienteResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.builder.ClienteBuilder;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Cliente;
import com.github.gerdanyjr.controle_mestre_backend.model.enums.SexoEnum;
import org.springframework.stereotype.Component;

@Component
public class ClienteAdapter {

    public Cliente toEntity(ClienteRequest clienteRequest) {
        return new ClienteBuilder()
                .withCpf(clienteRequest.cpf())
                .withNome(clienteRequest.nome())
                .withSexo(SexoEnum.fromCodigo(clienteRequest.sexo()))
                .withDataNascimento(clienteRequest.dataNascimento())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse
                .builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .sexo(cliente.getSexo().getCodigo())
                .dataNascimento(cliente.getDataNascimento())
                .build();
    }

}
