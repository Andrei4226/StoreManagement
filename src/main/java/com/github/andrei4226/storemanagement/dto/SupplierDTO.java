package com.github.andrei4226.storemanagement.dto;

import java.util.List;

public record SupplierDTO(
        Long id,
        String name,
        String contact,
        List<Long> productIds
) {}
