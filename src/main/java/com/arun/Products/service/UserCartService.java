package com.arun.Products.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arun.Products.advice.ResourceNotFoundException;
import com.arun.Products.dto.CartItemResponse;
import com.arun.Products.model.CartItem;
import com.arun.Products.model.Product;
import com.arun.Products.repo.CartRepository;
import com.arun.Products.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCartService {

    private final CartRepository cartRepo;
    private final ProductRepo productRepo;

    // 1. Add to Cart
    @CacheEvict(value = "userCart", key = "#username")
    public CartItemResponse addToCart(String username, String prodName, int quantity) {

        Product product = productRepo.findByProdName(prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + prodName));

        Optional<CartItem> existing = cartRepo.findByUsernameAndProdName(username, prodName);

        CartItem item;

        if (existing.isPresent()) {
            item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            log.info("Updated cart item quantity: user={}, product={}, quantity={}",
                    username, prodName, item.getQuantity());
        } else {
            item = new CartItem();
            item.setUsername(username);
            item.setProdName(prodName);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice());
            log.info("Added new cart item: user={}, product={}, quantity={}",
                    username, prodName, quantity);
        }

        CartItem saved = cartRepo.save(item);
        return mapToResponse(saved);
    }

    // 2. Update Quantity Only
    @CacheEvict(value = "userCart", key = "#username")
    public CartItemResponse updateCartQuantity(String username, String prodName, int quantity) {

        CartItem item = cartRepo.findByUsernameAndProdName(username, prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart: " + prodName));

        item.setQuantity(quantity);
        log.info("Set cart quantity: user={}, product={}, quantity={}",
                username, prodName, quantity);

        return mapToResponse(cartRepo.save(item));
    }

    // 3. Remove From Cart
    @CacheEvict(value = "userCart", key = "#username")
    public String removeFromCart(String username, String prodName) {
        CartItem item = cartRepo.findByUsernameAndProdName(username, prodName)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart: " + prodName));

        cartRepo.delete(item);
        log.info("Removed cart item: user={}, product={}", username, prodName);

        return "Item removed from cart: " + prodName;
    }

    // 4. View Cart
    @Transactional(readOnly = true)
    @Cacheable(value = "userCart", key = "#username")
    public List<CartItemResponse> getUserCart(String username) {

        List<CartItem> items = cartRepo.findByUsername(username);

        return items.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CartItemResponse mapToResponse(CartItem item) {
        return CartItemResponse.builder()
                .prodName(item.getProdName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .totalPrice(item.getPrice() * item.getQuantity())
                .build();
    }
}
