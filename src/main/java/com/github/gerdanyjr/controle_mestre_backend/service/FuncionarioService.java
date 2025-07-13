package com.github.gerdanyjr.controle_mestre_backend.service;

import com.github.gerdanyjr.controle_mestre_backend.dto.adapter.FuncionarioAdapter;
import com.github.gerdanyjr.controle_mestre_backend.dto.in.FuncionarioRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.FuncionarioResponse;
import com.github.gerdanyjr.controle_mestre_backend.model.entity.Funcionario;
import com.github.gerdanyjr.controle_mestre_backend.repository.IFuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final IFuncionarioRepository funcionarioRepository;
    private final FuncionarioAdapter funcionarioAdapter;

    public FuncionarioService(
            IFuncionarioRepository funcionarioRepository,
            FuncionarioAdapter funcionarioAdapter
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioAdapter = funcionarioAdapter;
    }

    public FuncionarioResponse createFuncionario(FuncionarioRequest funcionarioRequest) {
        verifyFuncionarioNotExists(funcionarioRequest.cpf());

        Funcionario funcionario = funcionarioAdapter.toEntity(funcionarioRequest);
        funcionario = funcionarioRepository.save(funcionario);

        return funcionarioAdapter.toResponse(funcionario);
    }

    public List<FuncionarioResponse> findAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        return funcionarios
                .stream()
                .map(funcionarioAdapter::toResponse)
                .toList();
    }

    public void deleteFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

        funcionarioRepository.delete(funcionario);
    }

    private void verifyFuncionarioNotExists(String cpf) {
        funcionarioRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cpf já cadastrado!"));
    }

}
