package com.github.andrei4226.storemanagement.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.andrei4226.storemanagement.enums.Category;
import com.github.andrei4226.storemanagement.entity.Views;

import java.time.LocalDate;
import java.util.List;

public class ProductDTO {

    @JsonView(Views.Admin.class)
    private Long id;

    @JsonView(Views.User.class)
    private String name;

    @JsonView(Views.User.class)
    private Double price;

    @JsonView(Views.Admin.class)
    private Integer stocks;

    @JsonView(Views.User.class)
    private Category category;

    @JsonView(Views.User.class)
    private List<String> tags;

    @JsonView(Views.Admin.class)
    private String code;

    @JsonView(Views.User.class)
    private LocalDate releaseDate;

    @JsonView(Views.Admin.class)
    private Long supplierId;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, Integer stocks, Category category, List<String> tags, String code, LocalDate releaseDate, Long supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stocks = stocks;
        this.category = category;
        this.tags = tags;
        this.code = code;
        this.releaseDate = releaseDate;
        this.supplierId = supplierId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stocks=" + stocks +
                ", category=" + category +
                ", tags=" + tags +
                ", code='" + code + '\'' +
                ", releaseDate=" + releaseDate +
                ", supplierId=" + supplierId +
                '}';
    }
}
