package com.github.andrei4226.storemanagement.repository;

import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
    List<Product> findByCategory(Category category);
}