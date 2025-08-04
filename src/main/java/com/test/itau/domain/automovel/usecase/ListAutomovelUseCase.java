package com.test.itau.domain.automovel.usecase;

import com.test.itau.domain.automovel.api.v1.request.AutomovelFilter;
import com.test.itau.domain.automovel.converter.AutomovelConverter;
import com.test.itau.domain.automovel.repository.AutomovelRepository;
import com.test.itau.shared.response.PageDTO;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListAutomovelUseCase implements UseCase<AutomovelFilter, PageDTO> {
    private final AutomovelRepository repository;
    private final AutomovelConverter converter;

    @Override
    public PageDTO execute(AutomovelFilter automovelFilter) {
        var automovelPage = repository.findAll(automovelFilter.toSpec(), automovelFilter.pageable());
        return new PageDTO(automovelPage.map(converter::convert));
    }
}
