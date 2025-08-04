package com.test.itau.domain.automovel.usecase;


import com.test.itau.domain.automovel.api.v1.request.AutomovelRequest;
import com.test.itau.domain.automovel.converter.AutomovelConverter;
import com.test.itau.domain.automovel.repository.AutomovelRepository;
import com.test.itau.exception.BusinessException;
import com.test.itau.shared.response.ApiServiceResponse;
import com.test.itau.shared.usecase.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAutomovelUseCase implements UseCase<AutomovelRequest, ApiServiceResponse> {
    private final AutomovelRepository repository;
    private final AutomovelConverter converter;

    @Transactional
    public ApiServiceResponse execute(AutomovelRequest request) {
        if (Objects.isNull(request)) {
            throw new BusinessException("Preencha os campos");
        }

        var automovel = repository.save(converter.convert(request));

        return ApiServiceResponse.builder()
                .id(automovel.getId())
                .message("Automovel cadastrado com sucesso")
                .build();
    }
}
