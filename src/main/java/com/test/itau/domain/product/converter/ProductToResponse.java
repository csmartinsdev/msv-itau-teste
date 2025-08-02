package com.test.itau.domain.product.converter;

import com.test.itau.domain.product.api.v1.response.ProductResponse;
import com.test.itau.domain.product.entity.Product;

public interface ProductToResponse {

    ProductResponse convert(Product product);
}
