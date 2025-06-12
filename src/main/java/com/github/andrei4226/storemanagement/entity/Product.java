package com.github.andrei4226.storemanagement.entity;

import com.github.andrei4226.storemanagement.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Product {

    @Getter
    @Setter
    @Id
    private Long id;

    private String name;
    private Double price;
    private Integer stocks;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @Size(max = 5)
    private List<@Size(max=20) String> tags = new ArrayList<>();

    @Column(unique = true, nullable = false)
    private String code;

    @PastOrPresent
    private LocalDate releaseDate;

    public Product(Long id, String name, Double price, Integer stocks, Category category, List<@Size(max = 20) String> tags, String code, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stocks = stocks;
        this.category = category;
        this.tags = tags;
        this.code = code;
        this.releaseDate = releaseDate;
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

    public @Size(max = 5) List<@Size(max = 20) String> getTags() {
        return tags;
    }

    public void setTags(@Size(max = 5) List<@Size(max = 20) String> tags) {
        this.tags = tags;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public @PastOrPresent LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@PastOrPresent LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stocks=" + stocks +
                ", category=" + category +
                ", tags=" + tags +
                ", code='" + code + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
