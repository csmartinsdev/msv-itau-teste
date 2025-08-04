package com.test.itau.domain.automovel.api.v1.request;

import com.test.itau.domain.automovel.entity.Automovel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public record AutomovelFilter(Long id, Pageable pageable) {
    public Specification<Automovel> toSpec() {
        return (root, query, builder) -> {
            if (Objects.nonNull(id)) {
                return builder.equal(root.get("id"), id);
            }

            return null;
        };

    }
}
