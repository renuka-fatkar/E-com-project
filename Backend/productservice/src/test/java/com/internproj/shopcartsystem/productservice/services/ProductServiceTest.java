package com.internproj.shopcartsystem.productservice.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.internproj.shopcartsystem.productservice.entities.Product;
import com.internproj.shopcartsystem.productservice.externalservice.RatingService;
import com.internproj.shopcartsystem.productservice.repositories.ProductRepository;

/**
 * @author renuk
 *
 */

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService service;
	
	@MockBean
	private ProductRepository repo;
	
	@MockBean
	RatingService ratingService;
	
	@BeforeEach
	void setup() {
		int prodId = 101;
		String category = "electronics";
		String name = "redmi11";
		Optional<Product> product = Optional.of(new Product(101, "hairOil", 800, "Beauty products", "100% natural",null,null));
		Product product1 = new Product(107, "hairOil", 800, "Beauty products", "100% natural",null,null);
		Mockito.when(repo.findById(prodId)).thenReturn(product);
		Mockito.when(repo.findAllByCategory(category)).thenReturn(Stream.of(new Product(102, "redmi11",11000,"electronics","excellent",null,null),new Product(103, "realmi10",13000,"electronics","excellent",null,null)).collect(Collectors.toList() ));
		Mockito.when(repo.findAllByName(name)).thenReturn(Stream.of(new Product(102, "redmi11",11000,"electronics","excellent",null,null)).collect(Collectors.toList() ));
		Mockito.when(ratingService.viewRatingsByProdId(prodId)).thenReturn(null);
		Mockito.when(repo.save(product1)).thenReturn(product1);
	}
	
	@Test
	public void testViewProduct() {
		int prodId = 101;
		assertEquals("hairOil", service.viewProduct(prodId).getName());
	}
	
	@Test
	public void testViewProductsByCategory() {
		String category = "electronics";
		assertEquals(2,service.viewProductsByCategory(category).size());
	}
	
	@Test
	public void testViewProductsByName() {
		String name = "redmi11";
		assertEquals(1,service.viewProductsByName(name).size());
	}
	
	@Test
	public void testAddProducts() {
		Product product1 = new Product(101, "hairOil", 800, "Beauty products", "100% natural",null,null);
		List<Product> products = new ArrayList<>();
		products.add(product1);
		Mockito.when(repo.saveAll(products)).thenReturn(products);
		assertEquals(products,service.addProducts(products));
	}
	
	@Test
	public void testAddProduct() {
		Product product1 = new Product(101, "hairOil", 800, "Beauty products", "100% natural",null,null);
		Mockito.when(repo.save(product1)).thenReturn(product1);
		assertEquals(product1,service.addProduct(product1));
		}
	
	@Test
	public void testremoveProduct() {
		int prodId = 101;
		Product product1 = new Product(101, "hairOil", 800, "Beauty products", "100% natural",null,null);
		//service.removeProduct(prodId);
		//verify(repo, times(1)).delete(product1);
		assertEquals("deleted",service.removeProduct(prodId));
	}
	
	@Test
	public void testupdateProduct() {
		Product product1 = new Product(101, "coconutOil", 800, "Beauty products", "100% natural",null,null);
		Product p = service.updateProduct(product1);
		assertEquals(product1,p);
	}
}
