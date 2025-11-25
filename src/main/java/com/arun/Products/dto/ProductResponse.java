package com.arun.Products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	private String id;
	
	private String prodName;
	
	private Double price;
	
	private Integer stock;
	
	private String availability;

	public String getId() {
		return id;
	}

	
	


}
