package com.github.andrei4226.storemanagement.unit.service;

import com.github.andrei4226.storemanagement.dto.ProductDTO;
import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.entity.Supplier;
import com.github.andrei4226.storemanagement.enums.Category;
import com.github.andrei4226.storemanagement.exception.DuplicateProductCodeException;
import com.github.andrei4226.storemanagement.exception.ProductNotFoundException;
import com.github.andrei4226.storemanagement.repository.ProductRepository;
import com.github.andrei4226.storemanagement.repository.SupplierRepository;
import com.github.andrei4226.storemanagement.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    ProductService productService;

    ProductDTO dto;
    Supplier supplier;
    Product product;

    @BeforeEach
    void setup() {
        supplier = new Supplier();
        supplier.setId(1L);

        dto = new ProductDTO();
        dto.setName("Phone");
        dto.setCode("PHN123");
        dto.setCategory(Category.ELECTRONICS);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tech");
        tags.add("new");
        dto.setTags(tags);
        dto.setPrice(900.0);
        dto.setStocks(5);
        dto.setReleaseDate(LocalDate.now());
        dto.setSupplierId(1L);

        product = new Product();
        product.setCode("PHN123");
        product.setTags(new ArrayList<>(tags));
        product.setPrice(900.0);
        product.setStocks(5);
        product.setName("Phone");
        product.setSupplier(supplier);
        product.setCategory(Category.ELECTRONICS);
    }

    @Test
    void testAddProduct_success() {
        when(productRepository.findByCode("PHN123")).thenReturn(null);
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Product saved = new Product();
        saved.setCode("PHN123");
        when(productRepository.save(any())).thenReturn(saved);
        Product result = productService.addProduct(dto);
        assertEquals("PHN123", result.getCode());
    }

    @Test
    void testAddProduct_duplicateCode_throws() {
        when(productRepository.findByCode("PHN123")).thenReturn(new Product());
        assertThrows(DuplicateProductCodeException.class, () -> {
            productService.addProduct(dto);
        });
    }

    @Test
    void testUpdateStock() {
        Product p = new Product();
        p.setCode("PHN123");
        p.setStocks(3);
        when(productRepository.findByCode("PHN123")).thenReturn(p);
        when(productRepository.save(any())).thenReturn(p);

        Product updated = productService.updateStock("PHN123", 10);
        assertEquals(10, updated.getStocks());
    }

    @Test
    void testRemoveTag() {
        Product p = new Product();
        p.setCode("PHN123");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        p.setTags(tags);
        when(productRepository.findByCode("PHN123")).thenReturn(p);
        when(productRepository.save(any())).thenReturn(p);

        Product result = productService.removeTag("PHN123", "tag2");
        assertEquals(1, result.getTags().size());
        assertFalse(result.getTags().contains("tag2"));
    }

    @Test
    void testUpdatePrice() {
        Product p = new Product();
        p.setCode("PHN123");
        p.setPrice(500.0);
        when(productRepository.findByCode("PHN123")).thenReturn(p);
        when(productRepository.save(any())).thenReturn(p);

        Product updated = productService.updatePrice("PHN123", 200.0);
        assertEquals(200.0, updated.getPrice());
    }

    @Test
    void testFindByCodeSuccess() {
        when(productRepository.findByCode("PHN123")).thenReturn(product);
        Product result = productService.findByCode("PHN123");
        assertEquals("Phone", result.getName());
    }

    @Test
    void testFindByCodeNotFound() {
        when(productRepository.findByCode("20")).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> productService.findByCode("20"));
    }

    @Test
    void testFindByCategorySuccess() {
        when(productRepository.findByCategory(Category.ELECTRONICS)).thenReturn(List.of(product));
        List<Product> result = productService.findByCategory(Category.ELECTRONICS);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByCategoryNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.findByCategory(null));
    }

    @Test
    void testAddTagSuccess() {
        when(productRepository.findByCode("PHN123")).thenReturn(product);
        when(productRepository.save(any())).thenReturn(product);
        Product result = productService.addTag("PHN123", "2025");
        assertTrue(result.getTags().contains("2025"));
    }

    @Test
    void testAddTagInvalidLength() {
        assertThrows(ProductNotFoundException.class, () -> productService.addTag("PHN123", "a"));
    }

    @Test
    void testUpdateListOfTagsSuccess() {
        when(productRepository.findByCode("PHN123")).thenReturn(product);
        ArrayList<String> newTags = new ArrayList<>();
        newTags.add("sale");
        newTags.add("device");
        product.setTags(new ArrayList<>(newTags));
        when(productRepository.save(any())).thenReturn(product);
        Product result = productService.updateListOfTags("PHN123", newTags);
        assertEquals(newTags, result.getTags());
    }

    @Test
    void testUpdateListOfTagsInvalid() {
        ArrayList<String> testTags = new ArrayList<>();
        testTags.add("tag1");
        testTags.add("tag2");
        testTags.add("tag3");
        testTags.add("tag4");
        testTags.add("tag5");
        testTags.add("tag6");
        product.setTags(new ArrayList<>(testTags));
        assertThrows(IllegalArgumentException.class, () -> productService.updateListOfTags("PHN123", testTags));
    }

    @Test
    void testDeleteProductByIdSuccess() {
        when(productRepository.existsById(1L)).thenReturn(true);
        productService.deleteProductById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testDeleteProductByIdNotFound() {
        when(productRepository.existsById(2L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProductById(2L));
    }

    @Test
    void testDeleteProductByCodeSuccess() {
        when(productRepository.findByCode("PHN123")).thenReturn(product);
        productService.deleteProductByCode("PHN123");
        verify(productRepository).delete(product);
    }

    @Test
    void testDeleteProductByCodeNotFound() {
        when(productRepository.findByCode("PHN123")).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductByCode("PHN123"));
    }

    @Test
    void testFindByPriceRange() {
        Product p1 = new Product();
        p1.setPrice(100.0);
        Product p2 = new Product();
        p2.setPrice(300.0);
        Product p3 = new Product();
        p3.setPrice(600.0);
        when(productRepository.findAll()).thenReturn(List.of(p1, p2, p3));
        List<Product> results = productService.findByPriceRange(200.0, 700.0);
        assertEquals(2, results.size());
    }

    @Test
    void testSearchByName() {
        Product p1 = new Product();
        p1.setName("Smartphone");
        Product p2 = new Product();
        p2.setName("Laptop");
        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = productService.searchByName("smart");
        assertEquals(1, result.size());
        assertEquals("Smartphone", result.get(0).getName());
    }

    @Test
    void testFindAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        List<Product> result = productService.findAllProducts();
        assertEquals(1, result.size());
    }
}
