package com.github.andrei4226.storemanagement.controller;

import com.github.andrei4226.storemanagement.dto.SupplierDTO;
import com.github.andrei4226.storemanagement.entity.Supplier;
import com.github.andrei4226.storemanagement.service.SupplierService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;

import static com.github.andrei4226.storemanagement.utils.ValidationUtils.*;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //add supplier
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/addSupplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody SupplierDTO supplierDTO) {
        validateSupplierName(supplierDTO.name());
        validateSupplierContact(supplierDTO.contact());
        logger.info("Supplier: {}", supplierDTO.name());
        return ResponseEntity.ok(supplierService.createSupplier(supplierDTO));
    }

    //all suppliers
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/allSuppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        logger.info("All suppliers");
        return ResponseEntity.ok(supplierService.findAll());
    }

    //view supplier by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/viewSupplier/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        validateId(id);
        logger.info("Supplier with ID {}", id);
        try {
            return ResponseEntity.ok(supplierService.findById(id));
        } catch (NoSuchElementException ex) {
            logger.warn("Supplier with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        } catch (Exception ex) {
            logger.error("Error supplier with ID {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    //delete the supplier by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/deleteSupplier/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        validateId(id);
        logger.info("Delete supplier with ID {}", id);
        try {
            supplierService.deleteById(id);
            return ResponseEntity.ok("Supplier deleted successfully.");
        } catch (NoSuchElementException ex) {
            logger.warn("Supplier not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        } catch (Exception ex) {
            logger.error("Error deleting supplier with ID {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // update supplier
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/updateSupplier/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        validateId(id);
        validateSupplierName(dto.name());
        validateSupplierContact(dto.contact());
        logger.info("Update supplier with ID {} to: {}", id, dto);
        try {
            return ResponseEntity.ok(supplierService.updateSupplier(id, dto));
        } catch (NoSuchElementException ex) {
            logger.warn("Supplier with ID {} not found for update", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        } catch (Exception ex) {
            logger.error("Error supplier with ID {}", id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
