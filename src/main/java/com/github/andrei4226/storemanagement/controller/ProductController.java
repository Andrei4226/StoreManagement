package com.github.andrei4226.storemanagement.controller;

import com.github.andrei4226.storemanagement.dto.ProductDTO;
import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.entity.Views;
import com.github.andrei4226.storemanagement.enums.Category;
import com.github.andrei4226.storemanagement.exception.TagNotFoundException;
import com.github.andrei4226.storemanagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.github.andrei4226.storemanagement.utils.ValidationUtils.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //add product
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Request to create a product with code: {}", productDTO.getCode());
        validateProductName(productDTO.getName());
        validateProductPrice(productDTO.getPrice());
        validateProductStock(productDTO.getStocks());
        validateProductCode(productDTO.getCode());
        Product savedProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }

    //get product bt code
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/viewProduct/{code}")
    public ResponseEntity<?> viewProductByCodeAdmin(@PathVariable String code) {
        try {
            Product product = productService.findByCode(code);
            ProductDTO dto = productService.mapToDTO(product);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (Exception ex) {
            logger.error("Error getting product by code {}", code, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    //update price
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{code}/price")
    public ResponseEntity<Product> updatePrice(@PathVariable String code, @RequestParam Double price) {
        validateProductCode(code);
        validateProductPrice(price);
        logger.info("Update the price of product from {} to {}", code, price);
        return ResponseEntity.ok(productService.updatePrice(code,price));
    }

    //update stock
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{code}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable String code, @RequestParam int stocks) {
        validateProductCode(code);
        validateProductStock(stocks);
        logger.info("Update the stock of product from {} to {}", code, stocks);
        return ResponseEntity.ok(productService.updateStock(code, stocks));
    }

    //all products for user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allProducts")
    public ResponseEntity<MappingJacksonValue> findAllProductsUser() {
        List<ProductDTO> list = productService.findAllProducts().stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        if(list.isEmpty()){
            logger.warn("No products found for user");
        } else {
            logger.info("List of products to user: {}", list.size());
        }
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setSerializationView(Views.User.class);
        return ResponseEntity.ok(mapping);
    }

    //all products for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/allProducts")
    public ResponseEntity<List<ProductDTO>> findAllProductsAdmin() {
        List<ProductDTO> list = productService.findAllProducts().stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            logger.warn("No products found for admin");
        } else {
            logger.info("List of products to admin: {}", list.size());
        }
        return ResponseEntity.ok(list);
    }

    //get products by category for user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/category/{category}")
    public ResponseEntity<MappingJacksonValue> findByCategoryUser(@PathVariable Category category) {
        validateCategory(category);
        List<ProductDTO> list = productService.findByCategory(category).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setSerializationView(Views.User.class);
        return ResponseEntity.ok(mapping);
    }

    //get products by category for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/category/{category}")
    public ResponseEntity<List<ProductDTO>> findByCategoryAdmin(@PathVariable Category category) {
        validateCategory(category);
        List<ProductDTO> list = productService.findByCategory(category).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // filter by price range for user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/price-range")
    public ResponseEntity<MappingJacksonValue> findByPriceRangeUser(@RequestParam double min, @RequestParam double max) {
        validatePriceRange(min, max);
        List<ProductDTO> list = productService.findByPriceRange(min, max).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setSerializationView(Views.User.class);
        return ResponseEntity.ok(mapping);
    }

    // filter by price range for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/price-range")
    public ResponseEntity<List<ProductDTO>> findByPriceRangeAdmin(@RequestParam double min, @RequestParam double max) {
        validatePriceRange(min, max);
        List<ProductDTO> list = productService.findByPriceRange(min, max).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //search by name for user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public ResponseEntity<MappingJacksonValue> searchByNameUser(@RequestParam String name) {
        validateSearchName(name);
        List<ProductDTO> list = productService.searchByName(name).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setSerializationView(Views.User.class);
        return ResponseEntity.ok(mapping);
    }

    //search by name for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/search")
    public ResponseEntity<List<ProductDTO>> searchByNameAdmin(@RequestParam String name) {
        validateProductName(name);
        List<ProductDTO> list = productService.searchByName(name).stream()
                .map(productService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //add a tag
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/{code}/tags/add")
    public ResponseEntity<Product> addTag(@PathVariable String code, @RequestParam String tag) {
        validateProductCode(code);
        validateTag(tag);
        logger.info("Add tag '{}' to product {}", tag, code);
        return ResponseEntity.ok(productService.addTag(code, tag));
    }

    //remove a tag
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/{code}/tags/remove")
    public ResponseEntity<?> removeTag(@PathVariable String code, @RequestParam String tag) {
        try {
            validateProductCode(code);
            validateTag(tag);
            logger.info("Remove tag '{}' from product {}", tag, code);
            return ResponseEntity.ok(productService.removeTag(code, tag));
        } catch (TagNotFoundException ex) {
            logger.warn("Tag '{}' not found in product {}", tag, code);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found: " + tag);
        } catch (IllegalArgumentException ex) {
            logger.warn("Validation failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error remove tag: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    //replace all tags
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{code}/tags")
    public ResponseEntity<Product> updateTags(@PathVariable String code, @RequestBody List<String> tags) {
        validateProductCode(code);
        validateTagList(tags);
        logger.info("Updating tags for product {} to {}", code, tags);
        return ResponseEntity.ok(productService.updateListOfTags(code, tags));
    }

    //delete product by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        validateId(id);
        logger.info("Deleting product with ID {}", id);
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            logger.warn("Product with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (Exception ex) {
            logger.error("Error product with id {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // delete product by code
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/code/{code}")
    public ResponseEntity<?> deleteByCode(@PathVariable String code) {
        validateProductCode(code);
        logger.info("Deleting product with code {}", code);
        try {
            productService.deleteProductByCode(code);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException | IllegalArgumentException ex) {
            logger.warn("Product with code {} not found", code);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (Exception ex) {
            logger.error("Error product with code {}", code, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
