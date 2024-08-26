package com.example.productservice.infrastructure.persistence.product;


import com.example.productservice.application.GetAllProductsPort;
import com.example.productservice.application.GetProductByIdPort;
import com.example.productservice.application.ProductRequest;
import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import com.example.productservice.infrastructure.persistence.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProductSqlPersistenceAdapter implements GetAllProductsPort, GetProductByIdPort {
    private final ProductRepository productRepository;

    public ProductSqlPersistenceAdapter(ProductRepository productRepository) {
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
    public Optional<Product> byId(ProductId id) {
        return productRepository.findById(id.value()).map(ProductMapper::toDomain);
    }
}
