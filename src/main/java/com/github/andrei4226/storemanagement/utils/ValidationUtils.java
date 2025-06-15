package com.github.andrei4226.storemanagement.utils;

import com.github.andrei4226.storemanagement.enums.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ValidationUtils {
    public static void validateProductName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name must be a valid string");
        }
        if(name.length() > 30) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name must be at most 30 characters");
        }
    }

    public static void validateProductPrice(Double price) {
        if (price == null || price <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        }
    }

    public static void validateProductStock(Integer stocks) {
        if (stocks == null || stocks < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stocks must be 0 or greater");
        }
    }

    public static void validateProductCode(String code) {
        if (code == null || code.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product code is required");
        }
    }

    public static void validateSearchName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search name must be a valid string");
        }
    }

    public static void validateTag(String tag) {
        if (tag == null || tag.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag must not be empty");
        }
        if (tag.length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag must be at most 20 characters");
        }
    }

    public static void validateTagList(List<String> tags) {
        if (tags == null || tags.size() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 5 tags");
        }
        for (String tag : tags) {
            validateTag(tag);
        }
    }

    public static void validateCategory(Category category) {
        if(category == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category must be valid");
        }
    }

    public static void validatePriceRange(double min, double max) {
        if (min <= 0 || max <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prices must be greater than 0");
        }
        if (min > max) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Min price must be lower than max price");
        }
    }

    public static void validateSupplierName(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier name must be a valid string");
        }
        if(name.length() > 30) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier name must be at most 30 characters");
        }
    }

    public static void validateSupplierContact(String contact) {
        if (contact == null || contact.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact is required");
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be a positive number");
        }
    }
}
