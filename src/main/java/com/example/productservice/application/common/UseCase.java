package com.example.productservice.application.common;

public interface UseCase<T, R> {
    R handle (T request);
}
