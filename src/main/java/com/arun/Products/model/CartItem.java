package com.arun.Products.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Min;
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
public class CartItem {

    @Id
    private String id;

    private String username;      // user who added to cart
    private String prodName;      // product name
    @Min(1)
    private Integer quantity;         // quantity selected
    private double price;         // price per item
}
