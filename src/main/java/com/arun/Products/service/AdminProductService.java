package com.arun.Products.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arun.Products.advice.ResourceNotFoundException;
import com.arun.Products.dto.ProductRequest;
import com.arun.Products.dto.ProductResponse;
import com.arun.Products.model.Product;
import com.arun.Products.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminProductService {

    private final ProductRepo repo;

    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse addProduct(ProductRequest request) {
        Product product = Product.builder()
                .prodName(request.getProdName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();

        Product saved = repo.save(product);
        log.info("Product saved with id={}, name={}", saved.getId(), saved.getProdName());
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(String prodName) {
        Product product = repo.findByProdName(prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + prodName));
        return mapToResponse(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse updateProduct(String prodName, ProductRequest request) {
        Product product = repo.findByProdName(prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + prodName));

        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product updated = repo.save(product);
        log.info("Product updated: {}", updated.getId());
        return mapToResponse(updated);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(String prodName) {
        Product product = repo.findByProdName(prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + prodName));
        repo.delete(product);
        log.info("Product deleted: {}", product.getId());
    }

    @Cacheable(value = "products")
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return repo.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .prodName(product.getProdName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
