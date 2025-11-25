package com.arun.Products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "cart_items")
public class CartItem {

    @Id
    private String id;

    @Indexed(unique = true)                 // helps get cart items for a user
    private String username;

    @Indexed                 // faster lookup
    private String prodName;

    private Integer quantity;

    private double price;
}
