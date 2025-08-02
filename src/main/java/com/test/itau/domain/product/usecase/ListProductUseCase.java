package com.test.itau.domain.product.usecase;

import com.test.itau.domain.product.api.v1.request.ProductFilter;
import com.test.itau.domain.product.converter.ProductConverter;
import com.test.itau.domain.product.entity.Product;
import com.test.itau.domain.product.repository.ProductRepository;
import com.test.itau.shared.response.PageDTO;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListProductUseCase implements UseCase<ProductFilter, PageDTO> {

    private final ProductRepository repository;
    private final ProductConverter converter;


    @Override
    public PageDTO execute(ProductFilter filter) {
        Page<Product> productsPage = repository.findAll(filter.toSpec(), filter.pageable());
        var response = productsPage.map(converter::convert);

        return new PageDTO(response);
    }
}
