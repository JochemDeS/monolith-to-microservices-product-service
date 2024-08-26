package com.example.productservice.application;

import com.example.productservice.domain.Product;
import org.springframework.data.domain.Page;

public interface GetAllProductsPort {
    Page<Product> all(ProductRequest request);
}
