package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {
}
