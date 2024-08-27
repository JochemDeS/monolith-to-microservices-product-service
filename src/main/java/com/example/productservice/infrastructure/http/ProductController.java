package com.example.productservice.infrastructure.http;

import com.example.productservice.application.GetAllProductsUseCase;
import com.example.productservice.application.GetProductsByIdInUseCase;
import com.example.productservice.application.ProductRequest;
import com.example.productservice.application.common.UseCase;
import com.example.productservice.domain.Product;
import com.example.productservice.domain.ProductId;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "All endpoints for the product")
public class ProductController {
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductsByIdInUseCase getProductsByIdInUseCase;

    public ProductController(GetAllProductsUseCase getAllProductsUseCase,
                             GetProductsByIdInUseCase getProductsByIdInUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductsByIdInUseCase = getProductsByIdInUseCase;
    }

    @PostMapping
    public ProductsResponseModel getProducts(@Valid @RequestBody ProductRequestModel request,
                                             Pageable pageable) {
        ProductRequest productRequest = ProductRequest.builder()
                .category(request.category())
                .brand(request.brand())
                .priceRange(request.priceRange())
                .pageable(pageable)
                .build();

        Page<Product> products = getAllProductsUseCase.handle(productRequest);

        return ProductsResponseModel.builder()
                .products(products.getContent().stream()
                        .map(this::mapToProductReadModel)
                        .toList())
                .page(products.getNumber())
                .size(products.getSize())
                .totalProducts((int) products.getTotalElements())
                .build();
    }

    @GetMapping
    public List<ProductDetailReadModel> getProduct(@RequestParam List<Integer> ids) {
        final var productIds = ids.stream()
                .map(id -> ProductId.builder()
                    .value((long) id)
                    .build())
                .toList();

        final var products = getProductsByIdInUseCase.handle(productIds);

        return products.stream()
                .map(this::mapToProductDetailModel)
                .toList();
    }

    private ProductReadModel mapToProductReadModel(Product product) {
        return ProductReadModel.builder()
                .id(product.id().value())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .build();
    }

    private ProductDetailReadModel mapToProductDetailModel(Product product) {
        return ProductDetailReadModel.builder()
                .id(product.id().value())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .stock(product.stock())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .image(product.image())
                .build();
    }
}
