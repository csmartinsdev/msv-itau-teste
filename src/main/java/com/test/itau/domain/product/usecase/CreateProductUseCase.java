package com.test.itau.domain.product.usecase;

import com.test.itau.domain.product.api.v1.request.ProductRequest;
import com.test.itau.domain.product.api.v1.response.ProductResponse;
import com.test.itau.domain.product.repository.ProductRepository;
import com.test.itau.exception.BusinessException;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateProductUseCase implements UseCase<ProductRequest, ProductResponse> {

    private final ProductRepository repository;

    @Override
    public ProductResponse execute(ProductRequest request) {
        if (Objects.isNull(request)) {
            throw new BusinessException("Payload vazio");
        }
        var product = request.convert();

        product = repository.save(product);
        return new ProductResponse(product.getExternalId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }
}
