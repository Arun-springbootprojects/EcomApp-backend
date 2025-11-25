//package com.arun.Employee;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.arun.Employee.dto.ProductRequest;
//import com.arun.Employee.dto.ProductResponse;
//import com.arun.Employee.model.Product;
//import com.arun.Employee.repo.ProductRepo;
//import com.arun.Employee.service.ProductService;
//
//public class ProductServiceTest {
//	
//	@Mock
//	private ProductRepo repo;
//	
//	@InjectMocks
//	private ProductService service;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		}
//	
//	@Test
//	void testAddProduct_Sucess() {
//		ProductRequest req = new ProductRequest();
//        req.setProdName("Laptop");
//        req.setPrice(85000.0);
//
//        Product savedProduct = new Product();
//        savedProduct.setId(101);
//        savedProduct.setProdName("Laptop");
//        savedProduct.setPrice(85000.0);
//
//        // Mock the repository save() method
//        when(repo.save(any(Product.class))).thenReturn(savedProduct);
//
//        // Act
//        ProductResponse response = service.addProduct(req);
//
//        // Assert
//        assertEquals(101, response.getId());
//        assertEquals("Laptop", response.getProdName());
//        assertEquals(85000.0, response.getPrice());
//
//        // Verify that repo.save() was called exactly once
//        verify(repo, times(1)).save(any(Product.class));
//    }
//	
//	@Test
//	void testGetProduct_sucess() {
//		Integer id = 101;
//		
//		Product xistingProduct = new Product();
//		xistingProduct.setId(101);
//		xistingProduct.setProdName("Laptop");
//		xistingProduct.setPrice(85000.0);
//		
//		when(repo.findById(id)).thenReturn(Optional.of(xistingProduct));
//		
//		Optional<Product> response = service.getProduct(id);
//		
//		assertNotNull(response);
//		assertEquals(101, response.getId());
//        assertEquals("Laptop", response.getProdName());
//        assertEquals(85000.0, response.getPrice());
//
//       
//        verify(repo, times(1)).findById(id);
//	}
//	
//}
