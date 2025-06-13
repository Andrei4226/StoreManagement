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
    public List<Product> findByCategory(Category category) {
        if (category == null){
            throw new IllegalArgumentException("Category must not be null");
        }
        return productRepository.findByCategory(category);
    }
    public Product addTag(String code, String newTag) {
        if(newTag.length() > 20 || newTag.length() < 5) {
            throw new IllegalArgumentException("Tags must contain at most 20 characters each and list of tags must contain at most 5 elements");
        }
        Product product = findByCode(code);
        product.addTag(newTag);
        return productRepository.save(product);
    }
    public Product removeTag(String code, String existTag) {
        Product product = findByCode(code);
        product.removeTag(existTag);
        return productRepository.save(product);
    }
    public Product updateListOfTags(String code, List<String> newListOfTags) {
        if(newListOfTags.size() > 5 || newListOfTags.stream().anyMatch(tag -> tag.length() > 20)) {
            throw new IllegalArgumentException("Tags must contain at most 5 elements of 20 characters each");
        }
        Product product = findByCode(code);
        product.setTags(newListOfTags);
        return productRepository.save(product);
    }
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)){
            throw new IllegalArgumentException("Product with ID " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }
    public void deleteProductByCode(String code) {
        Product product = productRepository.findByCode(code);
        if(product == null ) {
            throw new ProductNotFoundException(code);
        }
        productRepository.delete(product);
    }
    public Product updatePrice(String code, Double newPrice){
        if(newPrice<=0){
            throw new IllegalArgumentException("Price must be positive");
        }
        Product product = findByCode(code);
        product.setPrice(newPrice);
        return productRepository.save(product);
    }

    public Product updateStock(String code, int newStock){
        if(newStock<=0){
            throw new IllegalArgumentException("Stock must be positive");
        }
        Product product = findByCode(code);
        product.setStocks(newStock);
        return productRepository.save(product);
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findAll().stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .toList();
    }
    public List<Product> searchByName(String name) {
        return productRepository.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
