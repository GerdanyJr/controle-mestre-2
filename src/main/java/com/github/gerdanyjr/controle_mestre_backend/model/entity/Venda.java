package com.github.gerdanyjr.controle_mestre_backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;

}
