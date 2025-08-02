package com.test.itau.domain.product.api.v1.request;


import com.test.itau.domain.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRequest(String name, String description, BigDecimal price) {
    public Product convert() {
        var product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setExternalId(UUID.randomUUID());
        product.setDateTimeCreated(LocalDateTime.now());

        return product;
    }
}
