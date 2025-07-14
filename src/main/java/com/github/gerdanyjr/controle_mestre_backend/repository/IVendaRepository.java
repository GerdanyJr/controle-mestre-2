package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendaRepository extends JpaRepository<Venda, Long> {
}
