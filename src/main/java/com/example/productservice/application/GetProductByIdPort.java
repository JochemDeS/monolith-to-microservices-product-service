package com.example.productservice.application;


import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;

import java.util.Optional;

public interface GetProductByIdPort {
    Optional<Product> byId(ProductId id);
}
