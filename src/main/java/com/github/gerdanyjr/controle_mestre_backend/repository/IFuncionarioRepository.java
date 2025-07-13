package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
}
