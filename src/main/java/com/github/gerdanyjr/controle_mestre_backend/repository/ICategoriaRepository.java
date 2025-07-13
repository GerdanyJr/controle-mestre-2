package com.github.gerdanyjr.controle_mestre_backend.repository;

import com.github.gerdanyjr.controle_mestre_backend.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
