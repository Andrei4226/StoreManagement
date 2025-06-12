package com.github.andrei4226.storemanagement.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String code) {
        super("Product not found with code: " + code);
    }
}
