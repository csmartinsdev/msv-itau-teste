package com.test.itau.domain.automovel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "automovel")
public class Automovel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String marca;
    private String modelo;
    private BigDecimal valor;
    private LocalDate dataCadastro;
}
