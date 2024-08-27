package com.example.productservice.infrastructure.persistence.product;


import com.example.productservice.application.GetAllProductsPort;
import com.example.productservice.application.GetProductByIdInPort;
import com.example.productservice.application.ProductRequest;
import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import com.example.productservice.infrastructure.persistence.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductSqlPersistenceAdapterIn implements GetAllProductsPort, GetProductByIdInPort {
    private final ProductRepository productRepository;

    public ProductSqlPersistenceAdapterIn(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> all(ProductRequest request) {
        Specification<ProductEntity> specification = Specification.where(ProductSpecs.byCategory(request.category()))
                        .and(ProductSpecs.byBrand(request.brand()))
                        .and(ProductSpecs.byPriceRange(request.priceRange()));
        return productRepository.findAll(specification, request.pageable()).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> byIdIn(List<ProductId> ids) {
        final var productIds = ids.stream()
                .map(ProductId::value)
                .toList();

        return productRepository.findAllByIdIn(productIds).stream()
                .map(ProductMapper::toDomain)
                .toList();
    }
}
