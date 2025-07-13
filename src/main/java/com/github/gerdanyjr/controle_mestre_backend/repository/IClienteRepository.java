package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
