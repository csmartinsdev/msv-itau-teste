package com.test.itau.domain.automovel.api.v1;

import com.test.itau.domain.automovel.api.v1.request.AutomovelFilter;
import com.test.itau.domain.automovel.api.v1.request.AutomovelRequest;
import com.test.itau.domain.automovel.enums.AutomovelSortBy;
import com.test.itau.domain.automovel.usecase.CreateAutomovelUseCase;
import com.test.itau.domain.automovel.usecase.ListAutomovelUseCase;
import com.test.itau.shared.models.SortDirection;
import com.test.itau.shared.response.ApiServiceResponse;
import com.test.itau.shared.response.PageDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/automovel")
public class AutomovelController {
    private static final String URI = "/automovel/{id}";

    private final CreateAutomovelUseCase createAutomovelUseCase;
    private final ListAutomovelUseCase listAutomovelUseCase;

    @PostMapping("/cadastro-automovel")
    public ResponseEntity<ApiServiceResponse> create(@RequestBody @Valid AutomovelRequest request, UriComponentsBuilder uriComponentsBuilder) {
        log.info("Init add automovel: {}", request);
        var response = createAutomovelUseCase.execute(request);
        URI uri = uriComponentsBuilder.path(URI)
                .buildAndExpand(response.getId())
                .toUri();

        log.info("Finished add automovel: {}", response);
        return ResponseEntity.created(uri).body(response);
    }


    @GetMapping("/listagem")
    public ResponseEntity<PageDTO> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam(value = "sort", defaultValue = "MARCA") AutomovelSortBy sort,
                                        @RequestParam(value = "direction", defaultValue = "ASC") SortDirection direction,
                                        @RequestParam(required = false) Long id) {

        var pageable = PageRequest.of(page, size, direction.toDomainDirection(), sort.getFieldJpa());
        log.info("Init List automovel: {} {}", pageable, id);
        var filter = new AutomovelFilter(id, pageable);
        var response = listAutomovelUseCase.execute(filter);
        log.info("Finished list automovel: {}", response.getTotalElements());

        return ResponseEntity.ok(response);
    }

}
