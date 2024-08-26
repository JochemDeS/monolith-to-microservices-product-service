package com.example.productservice.infrastructure.persistence;


import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import com.example.productservice.infrastructure.persistence.product.ProductEntity;

public class ProductMapper {
    public static Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(ProductId.builder()
                        .value(productEntity.getId())
                        .build())
                .title(productEntity.getTitle())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .brand(BrandMapper.toDomain(productEntity.getBrand()))
                .category(CategoryMapper.toDomain(productEntity.getCategory()))
                .thumbnail(productEntity.getThumbnail())
                .image(productEntity.getImage())
                .build();
    }

    public static ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.id().value())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .stock(product.stock())
                .brand(BrandMapper.toEntity(product.brand()))
                .category(CategoryMapper.toEntity(product.category()))
                .thumbnail(product.thumbnail())
                .image(product.image())
                .build();
    }
}
