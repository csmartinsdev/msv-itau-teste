package com.test.itau.domain.product.api.v1;

import com.test.itau.domain.product.api.v1.request.ProductFilter;
import com.test.itau.domain.product.api.v1.request.ProductRequest;
import com.test.itau.domain.product.api.v1.response.ProductResponse;
import com.test.itau.domain.product.enums.ProductSortBy;
import com.test.itau.domain.product.usecase.CreateProductUseCase;
import com.test.itau.domain.product.usecase.ListProductUseCase;
import com.test.itau.domain.product.usecase.UploadProductFile;
import com.test.itau.shared.models.SortDirection;
import com.test.itau.shared.response.ApiServiceResponse;
import com.test.itau.shared.response.PageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private static final String URI = "/product/{externalId}";

    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listUseCase;
    private final UploadProductFile uploadProductFile;

    @PostMapping
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest request, UriComponentsBuilder uriComponentsBuilder) {
        log.info("Adding product {}", request);
        var response = createProductUseCase.execute(request);

        URI uri = uriComponentsBuilder.path(URI)
                .buildAndExpand(response.externalId())
                .toUri();

        log.info("Added product {}", response);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<PageDTO> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                        @RequestParam(value = "sort", defaultValue = "NAME") ProductSortBy sort,
                                        @RequestParam(value = "direction", defaultValue = "ASC") SortDirection direction,
                                        @RequestParam(required = false) String name) {

        var pageable = PageRequest.of(page, size, direction.toDomainDirection(), sort.getFieldJpa());
        log.info("Listing all products {} | filtering: {}", pageable, name);
        var filter = new ProductFilter(name, pageable);
        var response = listUseCase.execute(filter);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ApiServiceResponse> upload(@RequestParam MultipartFile file) {
        log.info("Init Uploading file {}", file.getOriginalFilename());
        var response = uploadProductFile.execute(file);
        log.info("Finished Upload file {}", file.getOriginalFilename());
        return ResponseEntity.ok(response);
    }
}
