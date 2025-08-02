package com.test.itau.domain.product.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.domain.product.api.v1.response.ProductResponse;
import com.test.itau.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductConverter implements ProductToResponse {
    private final ObjectMapper mapper;

    @Override
    public ProductResponse convert(Product product) {
        if (Objects.isNull(product)) return null;

        return new ProductResponse(product.getExternalId(), product.getName(),
                product.getDescription(), product.getPrice());
    }
}
