package com.arun.Products.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.Products.dto.CartItemResponse;
import com.arun.Products.dto.CartRequest;
import com.arun.Products.service.UserCartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
@Validated
@CrossOrigin
@Slf4j
public class UserCartController {

    private final UserCartService cartService;

    // 1. Add to Cart
    @PostMapping("/add")
    public ResponseEntity<CartItemResponse> addToCart(
            @Valid @RequestBody CartRequest request,
            Principal principal) {

        String username = principal.getName();
        log.info("Add to cart: user={}, product={}, qty={}",
                username, request.getProdName(), request.getQuantity());

        CartItemResponse response =
                cartService.addToCart(username, request.getProdName(), request.getQuantity());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2. Update item quantity
    @PutMapping("/update")
    public ResponseEntity<CartItemResponse> updateQuantity(
            @Valid @RequestBody CartRequest request,
            Principal principal) {

        String username = principal.getName();
        log.info("Update cart: user={}, product={}, qty={}",
                username, request.getProdName(), request.getQuantity());

        CartItemResponse response =
                cartService.updateCartQuantity(username, request.getProdName(), request.getQuantity());

        return ResponseEntity.ok(response);
    }

    // 3. Remove item
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItem(
            @RequestParam String prodName,
            Principal principal) {

        String username = principal.getName();
        log.info("Remove from cart: user={}, product={}", username, prodName);

        cartService.removeFromCart(username, prodName);
        return ResponseEntity.noContent().build();
    }

    // 4. Get user cart
    @Cacheable(value = "userCart", key = "#principal.name")
    @GetMapping("/getAll")
    public ResponseEntity<List<CartItemResponse>> viewCart(Principal principal) {
        String username = principal.getName();
        log.info("View cart for user={}", username);

        List<CartItemResponse> cart = cartService.getUserCart(username);
        return ResponseEntity.ok(cart);
    }
}
