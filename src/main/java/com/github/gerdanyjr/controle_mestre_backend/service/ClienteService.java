package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ClienteAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.ClienteRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ClienteResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Cliente;
import com.github.gerdanyjr.controle_mestre_backend.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final IClienteRepository clienteRepository;
    private final ClienteAdapter clienteAdapter;

    public ClienteService(
            IClienteRepository clienteRepository,
            ClienteAdapter clienteAdapter
    ) {
        this.clienteRepository = clienteRepository;
        this.clienteAdapter = clienteAdapter;
    }

    public ClienteResponse create(ClienteRequest clienteRequest) {
        verifyCpfNotExists(clienteRequest.cpf());
        Cliente cliente = clienteAdapter.toEntity(clienteRequest);
        Cliente created = clienteRepository.save(cliente);

        return clienteAdapter.toResponse(created);
    }

    public List<ClienteResponse> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes
                .stream()
                .map(clienteAdapter::toResponse)
                .toList();
    }

    public void delete(Long id) {
        Cliente foundCliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteRepository.delete(foundCliente);
    }

    private void verifyCpfNotExists(String cpf) {
        clienteRepository
                .findByCpf(cpf)
                .ifPresent(cliente -> {
                    throw new RuntimeException("CPF já cadastrado");
                });
    }
}
