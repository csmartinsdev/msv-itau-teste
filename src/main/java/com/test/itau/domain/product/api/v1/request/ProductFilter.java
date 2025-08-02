package com.test.itau.domain.product.api.v1.request;

import com.test.itau.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public record ProductFilter(String name, Pageable pageable) {
    public Specification<Product> toSpec() {
        return (root, query, builder) -> {
            if (Objects.nonNull(name)) {
                return builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
            return null;
        };
    }
}
