package com.test.itau.domain.automovel.enums;

import lombok.Getter;

@Getter
public enum AutomovelSortBy {
    MARCA("marca", "marca"),
    MODELO("modelo", "modelo"),
    DATA_CRIACAO("dataCriacao", "dataCriacao");

    private final String fieldJpa;
    private final String fieldNative;

    AutomovelSortBy(String fieldJpa, String fieldNative) {
        this.fieldJpa = fieldJpa;
        this.fieldNative = fieldNative;
    }
}
