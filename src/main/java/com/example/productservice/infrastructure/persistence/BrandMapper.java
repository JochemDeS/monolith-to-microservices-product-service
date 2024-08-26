package com.example.productservice.infrastructure.persistence;


import com.example.productservice.domain.Brand;
import com.example.productservice.domain.BrandId;
import com.example.productservice.infrastructure.persistence.brand.BrandEntity;

public class BrandMapper {
    public static Brand toDomain(BrandEntity brandEntity) {
        return Brand.builder()
                .id(BrandId.builder()
                        .id(brandEntity.getId())
                        .build())
                .name(brandEntity.getName())
                .build();
    }

    public static BrandEntity toEntity(Brand brand) {
        return BrandEntity.builder()
                .id(brand.id().id())
                .name(brand.name())
                .build();
    }
}
