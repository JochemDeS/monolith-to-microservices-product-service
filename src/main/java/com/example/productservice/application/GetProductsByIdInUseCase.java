package com.example.productservice.application;

import com.example.productservice.application.common.UseCase;
import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByIdInUseCase implements UseCase<List<ProductId>, List<Product>> {
    private final GetProductByIdInPort getProductByIdInPort;

    public GetProductsByIdInUseCase(GetProductByIdInPort getProductByIdInPort) {
        this.getProductByIdInPort = getProductByIdInPort;
    }

    @Override
    public List<Product> handle(List<ProductId> request) {
        return getProductByIdInPort.byIdIn(request);
    }
}
