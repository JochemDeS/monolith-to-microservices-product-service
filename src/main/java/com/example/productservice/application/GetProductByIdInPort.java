package com.example.productservice.application;


import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;

import java.util.List;

public interface GetProductByIdInPort {
    List<Product> byIdIn(List<ProductId> id);
}
