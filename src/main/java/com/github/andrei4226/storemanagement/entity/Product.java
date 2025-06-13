package com.github.andrei4226.storemanagement.entity;

import com.github.andrei4226.storemanagement.enums.Category;
import com.github.andrei4226.storemanagement.exception.TagNotFoundException;
import com.github.andrei4226.storemanagement.interfaces.Taggable;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Taggable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stocks;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @CollectionTable(name="product_tags", joinColumns = @JoinColumn(name="product_id"))
    @Column(name="tag")
    @Size(max = 5)
    private List<@Size(max=20) String> tags = new ArrayList<>();

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public void addTag(String tag){
        if(tag == null || tag.length() > 20) {
            throw new IllegalArgumentException("Tag must be non-null and at most 20 characters long");
        }
        if(this.tags.size() >= 5 ) {
            throw new IllegalArgumentException("Cannot have more than 5 tags");
        }
        this.tags.add(tag);
    }

    @Override
    public void removeTag(String tag) {
        if(!this.tags.contains(tag)) {
            throw new TagNotFoundException(tag);
        }
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Long getId() {
        return id;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
