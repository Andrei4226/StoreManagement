package com.github.andrei4226.storemanagement.unit.service;

import com.github.andrei4226.storemanagement.dto.SupplierDTO;
import com.github.andrei4226.storemanagement.entity.Supplier;
import com.github.andrei4226.storemanagement.repository.SupplierRepository;
import com.github.andrei4226.storemanagement.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierService supplierService;

    Supplier supplier;
    SupplierDTO dto;

    @BeforeEach
    void setup() {
        supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Test");
        supplier.setContact("test@contact.com");
        dto = new SupplierDTO(1L,"Test", "test@contact.com");
    }

    @Test
    void testCreateSupplier() {
        when(supplierRepository.save(any())).thenReturn(supplier);
        Supplier result = supplierService.createSupplier(dto);
        assertEquals("Test", result.getName());
    }

    @Test
    void testFindAll() {
        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier));
        List<Supplier> result = supplierService.findAll();
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    void testFindByIdSucces() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        Supplier result = supplierService.findById(1L);
        assertEquals("Test", result.getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(supplierRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> supplierService.findById(2L));
    }

    @Test
    void testDeleteByIdSuccess() {
        when(supplierRepository.existsById(1L)).thenReturn(true);
        supplierService.deleteById(1L);
        verify(supplierRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(supplierRepository.existsById(2L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> supplierService.deleteById(2L));
    }

    @Test
    void testUpdateSupplierSuccess() {
        Supplier existing = new Supplier();
        existing.setId(1L);
        existing.setName("Old");
        existing.setContact("old@test.com");

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(supplierRepository.save(any())).thenReturn(supplier);

        Supplier updated = supplierService.updateSupplier(1L, dto);
        assertEquals("Test", updated.getName());
        assertEquals("test@contact.com", updated.getContact());
    }

    @Test
    void testUpdateSupplierNotFound() {
        when(supplierRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> supplierService.updateSupplier(2L, dto));
    }
}