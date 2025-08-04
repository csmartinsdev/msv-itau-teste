package com.test.itau.domain.automovel.api.v1.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AutomovelResponse {
    private Long id;
    private String marca;
    private String modelo;
    private BigDecimal valor;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataCadastro;
}
