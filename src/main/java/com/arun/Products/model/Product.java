package com.arun.Products.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	private String id;
	
	
	private String prodName;
	
	
	private Double price;
	
	private Integer stock;
	
	public boolean isInStock() {
		return stock > 0;
	}

	
	

}
