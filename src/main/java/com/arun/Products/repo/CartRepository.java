package com.arun.Products.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.arun.Products.model.CartItem;

public interface CartRepository extends MongoRepository<CartItem, String> {

    Optional<CartItem> findByUsernameAndProdName(String username, String prodName);

    void deleteByUsernameAndProdName(String username, String prodName);

    List<CartItem> findByUsername(String username);
}

