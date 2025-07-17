package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVendaRepository extends JpaRepository<Venda, Long> {
    @Query("SELECT v FROM Venda v WHERE v.cliente.id = :clienteId")
    List<Venda> findByCliente(@Param("clienteId") Long clienteId);

    @Query("SELECT v FROM Venda v WHERE v.funcionario.id = :funcionario")
    List<Venda> findByFuncionario(@Param("funcionarioId") Long funcionarioId);
}
