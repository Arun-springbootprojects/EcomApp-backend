package com.arun.Products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.Products.dto.ProductRequest;
import com.arun.Products.dto.ProductResponse;
import com.arun.Products.service.AdminProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminProductController {

    private final AdminProductService service;

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        log.info("Admin adding product: {}", request.getProdName());
        ProductResponse response = service.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("get/{prodName}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String prodName) {
        log.info("Fetching product by name: {}", prodName);
        ProductResponse response = service.getProduct(prodName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{prodName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String prodName,
            @Valid @RequestBody ProductRequest request) {
        log.info("Updating product: {}", prodName);
        ProductResponse response = service.updateProduct(prodName, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{prodName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String prodName) {
        log.info("Deleting product: {}", prodName);
        service.deleteProduct(prodName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> getAll() {
        log.info("Fetching all products");
        List<ProductResponse> products = service.getAll();
        return ResponseEntity.ok(products);
    }
}
