package com.test.itau.shared.models;

import org.springframework.data.domain.Sort;

public enum SortDirection {
    ASC, DESC;

    public Sort.Direction toDomainDirection() {
        return this == ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
