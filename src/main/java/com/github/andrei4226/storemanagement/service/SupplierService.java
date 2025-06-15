package com.github.andrei4226.storemanagement.service;

import com.github.andrei4226.storemanagement.dto.SupplierDTO;
import com.github.andrei4226.storemanagement.entity.Supplier;
import com.github.andrei4226.storemanagement.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(SupplierDTO dto) {
        return supplierRepository.save(new Supplier(null, dto.name(), dto.contact(), new ArrayList<>()));
    }
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
    public Supplier findById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Suppliers with ID " + id + " not found"));
    }

    public void deleteById(Long id) {
        if(!supplierRepository.existsById(id)) {
            throw new IllegalArgumentException("Supplier with ID " + id + " does not exist");
        }
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplier(Long id, SupplierDTO dto) {
        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier with ID " + id + " not found"));
        existing.setName(dto.name());
        existing.setContact(dto.contact());
        return supplierRepository.save(existing);
    }
}
