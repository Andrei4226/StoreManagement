package com.github.andrei4226.storemanagement.service;

import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.enums.Category;
import com.github.andrei4226.storemanagement.exception.DuplicateProductCodeException;
import com.github.andrei4226.storemanagement.exception.ProductNotFoundException;
import com.github.andrei4226.storemanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product addProduct(Product product) {
        if(productRepository.findByCode(product.getCode()) != null) {
            throw new DuplicateProductCodeException(product.getCode());
        }
        if(product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        return productRepository.save(product);
    }
    public Product findByCode(String code) {
        Product product = productRepository.findByCode(code);
        if(product == null) {
            throw new ProductNotFoundException(code);
        }
        return product;
    }
}
