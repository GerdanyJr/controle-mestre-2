package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

    @Query("SELECT pv FROM ProdutoVenda pv WHERE pv.produto.codigo = :produtoId")
    List<ProdutoVenda> findAllByProdutoId(@Param("produtoId") Long produtoId);
}
