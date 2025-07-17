package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.FuncionarioAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.FuncionarioRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.FuncionarioResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.ConflitoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.EntidadeEmUsoException;
import com.github.gerdanyjr.controle_mestre_backend.model.exceptions.NaoEncontradoException;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Funcionario;
import com.github.gerdanyjr.controle_mestre_backend.repository.IFuncionarioRepository;
import com.github.gerdanyjr.controle_mestre_backend.repository.IVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final IFuncionarioRepository funcionarioRepository;
    private final IVendaRepository vendaRepository;
    private final FuncionarioAdapter funcionarioAdapter;

    public FuncionarioService(
            IFuncionarioRepository funcionarioRepository,
            IVendaRepository vendaRepository,
            FuncionarioAdapter funcionarioAdapter
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.vendaRepository = vendaRepository;
        this.funcionarioAdapter = funcionarioAdapter;
    }

    @Transactional(rollbackFor = Exception.class)
    public FuncionarioResponse criar(FuncionarioRequest funcionarioRequest) {
        verificarCpfNaoExiste(funcionarioRequest.cpf());

        Funcionario funcionario = funcionarioAdapter.toEntity(funcionarioRequest);
        funcionario = funcionarioRepository.save(funcionario);

        return funcionarioAdapter.toResponse(funcionario);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> buscarTodos() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        return funcionarios
                .stream()
                .map(funcionarioAdapter::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarPorCpf(String cpf) {
        Funcionario funcionario = funcionarioRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new NaoEncontradoException("Funcionário não encontrado!"));

        return funcionarioAdapter.toResponse(funcionario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Funcionário não encontrado!"));

        List<Venda> vendas = vendaRepository.findByCliente(funcionario.getId());

        if (!vendas.isEmpty()) {
            throw new EntidadeEmUsoException("Este funcionario possui vendas vinculadas e não pode ser removido");
        }

        funcionarioRepository.delete(funcionario);
    }

    private void verificarCpfNaoExiste(String cpf) {
        funcionarioRepository
                .findByCpf(cpf)
                .ifPresent((funcionario) -> {
                    throw new ConflitoException("Cpf já cadastrado!");
                });
    }

}
