package com.test.itau.domain.automovel.converter;

import com.test.itau.domain.automovel.api.v1.request.AutomovelRequest;
import com.test.itau.domain.automovel.api.v1.response.AutomovelResponse;
import com.test.itau.domain.automovel.entity.Automovel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AutomovelConverter implements AutomovelToResponse, RequestToAutomovel {
    @Override
    public AutomovelResponse convert(Automovel automovel) {
        return AutomovelResponse.builder()
                .id(automovel.getId())
                .marca(automovel.getMarca())
                .modelo(automovel.getModelo())
                .valor(automovel.getValor())
                .dataCadastro(automovel.getDataCadastro())
                .build();
    }

    @Override
    public Automovel convert(AutomovelRequest request) {
        var automovel = new Automovel();
        automovel.setMarca(request.getMarca());
        automovel.setModelo(request.getModelo());
        automovel.setValor(request.getValor());
        automovel.setDataCadastro(LocalDate.now());

        return automovel;
    }
}
