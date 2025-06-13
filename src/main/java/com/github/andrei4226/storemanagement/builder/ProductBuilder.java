package com.github.andrei4226.storemanagement.builder;

import com.github.andrei4226.storemanagement.entity.Product;
import com.github.andrei4226.storemanagement.entity.Supplier;
import com.github.andrei4226.storemanagement.enums.Category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    private String name;
    private Double price;
    private Integer stocks;
    private Category category;
    private List<String> tags = new ArrayList<>();
    private String code;
    private LocalDate releaseDate;
    private Supplier supplier;

    public ProductBuilder name(String name) {
        this.name=name;
        return this;
    }

    public ProductBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder stocks(Integer stocks) {
        this.stocks = stocks;
        return this;
    }

    public ProductBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder tags(List<String> tags) {
        this.tags=tags;
        return this;
    }

    public ProductBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ProductBuilder releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public ProductBuilder supplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }
    public Product build() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStocks(stocks);
        product.setCategory(category);
        product.setTags(tags);
        product.setCode(code);
        product.setReleaseDate(releaseDate);
        product.setSupplier(supplier);
        return product;
    }
}

