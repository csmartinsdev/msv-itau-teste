package com.test.itau.domain.product.enums;

import lombok.Getter;

@Getter
public enum ProductSortBy {
    NAME("name", "name"),
    DESC("description", "description"),
    PRICE("price", "price"),
    DATE_CREATION("dateTimeCreation", "date_time_creation");

    private final String fieldJpa;
    private final String fieldNative;

    ProductSortBy(String fieldJpa, String fieldNative) {
        this.fieldJpa = fieldJpa;
        this.fieldNative = fieldNative;
    }
}
