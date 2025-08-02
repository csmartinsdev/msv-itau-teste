package com.test.itau.domain.product.api.v1.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID externalId, String name, String description, BigDecimal price) {
}
