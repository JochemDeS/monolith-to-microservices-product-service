package com.example.productservice.application;

import com.example.productservice.application.common.UseCase;
import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import com.example.productservice.application.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCase implements UseCase<ProductId, Product> {
    private final GetProductByIdPort getProductByIdPort;

    public GetProductByIdUseCase(GetProductByIdPort getProductByIdPort) {
        this.getProductByIdPort = getProductByIdPort;
    }

    @Override
    public Product handle(ProductId request) {
        return getProductByIdPort.byId(request)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
