package com.test.itau.domain.automovel.api.v1.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AutomovelRequest {
    @NotBlank(message = "Marca deve ser preenchido")
    private String marca;
    @NotBlank(message = "Modelo deve ser preenchido")
    private String modelo;
    @NotNull(message = "Valor deve ser preenchido")
    private BigDecimal valor;
}
