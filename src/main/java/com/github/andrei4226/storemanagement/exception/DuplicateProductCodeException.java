package com.github.andrei4226.storemanagement.exception;

public class DuplicateProductCodeException extends RuntimeException {
    public DuplicateProductCodeException(String code) {
        super("Product with code " + code + " already exists");
    }
}
