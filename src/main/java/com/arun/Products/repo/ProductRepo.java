package com.arun.Products.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arun.Products.dto.ProductResponse;
import com.arun.Products.model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

	
	Optional<Product> findByProdName(String prodName);

	boolean existsByProdName(String prodName);

	void deleteByProdName(String prodName);

	Product save(ProductResponse product);

	
	
}
