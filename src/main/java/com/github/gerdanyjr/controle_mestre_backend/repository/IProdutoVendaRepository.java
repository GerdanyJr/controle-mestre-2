package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
