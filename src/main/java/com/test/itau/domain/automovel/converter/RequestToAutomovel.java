package com.test.itau.domain.automovel.converter;

import com.test.itau.domain.automovel.api.v1.request.AutomovelRequest;
import com.test.itau.domain.automovel.entity.Automovel;

public interface RequestToAutomovel {
    Automovel convert(AutomovelRequest request);
}
