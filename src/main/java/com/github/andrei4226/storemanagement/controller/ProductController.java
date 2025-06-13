package com.github.andrei4226.storemanagement.controller;

import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping("{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(productService.findByCode(code));
    }
    @PutMapping("/{code}/price")
    public ResponseEntity<Product> updatePrice(@PathVariable String code, @RequestParam Double price) {
        return ResponseEntity.ok(productService.updatePrice(code,price));
    }
    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
