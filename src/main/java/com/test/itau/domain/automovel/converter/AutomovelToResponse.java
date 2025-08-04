package com.test.itau.domain.automovel.converter;

import com.test.itau.domain.automovel.api.v1.response.AutomovelResponse;
import com.test.itau.domain.automovel.entity.Automovel;

public interface AutomovelToResponse {
    AutomovelResponse convert(Automovel automovel);
}
