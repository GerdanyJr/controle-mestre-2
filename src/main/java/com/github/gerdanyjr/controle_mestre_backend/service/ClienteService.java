package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.ClienteAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.ClienteRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ClienteResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.ConflitoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.EntidadeEmUsoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.NaoEncontradoException;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Cliente;
import com.github.gerdanyjr.controle_mestre_backend.repository.IClienteRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final IClienteRepository clienteRepository;
    private final IVendaRepository vendaRepository;
    private final ClienteAdapter clienteAdapter;

    public ClienteService(
            IClienteRepository clienteRepository,
            IVendaRepository vendaRepository,
            ClienteAdapter clienteAdapter
    ) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
        this.clienteAdapter = clienteAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public ClienteResponse criar(ClienteRequest clienteRequest) {
        verifyCpfNotExists(clienteRequest.cpf());
        Cliente cliente = clienteAdapter.toEntity(clienteRequest);
        Cliente created = clienteRepository.save(cliente);

        return clienteAdapter.toResponse(created);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> buscarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes
                .stream()
                .map(clienteAdapter::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorCpf(String cpf) {
        Cliente foundCliente = clienteRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));

        return clienteAdapter.toResponse(foundCliente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) {
        Cliente foundCliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));

        List<Venda> vendas = vendaRepository.findByCliente(foundCliente.getId());

        if (!vendas.isEmpty()) {
            throw new EntidadeEmUsoException("Este cliente possui vendas vinculadas e não pode ser removido");
        }

        clienteRepository.delete(foundCliente);
    }

    private void verifyCpfNotExists(String cpf) {
        clienteRepository
                .findByCpf(cpf)
                .ifPresent(cliente -> {
                    throw new ConflitoException("CPF já cadastrado");
                });
    }
}
