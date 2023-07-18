package com.internproj.shopcartsystem.productservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.internproj.shopcartsystem.productservice.entities.Product;
//the data will exists while running the test, once the testing is done, it will be flushed
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepo; 
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setup() {
		Product product = new Product(105, "hairOil", 800, "Beauty products", "100% natural",null,null);
		entityManager.persist(product);
		Product product1 = new Product(111, "redmi11", 11000, "electronics", "excellent",null,null);
		entityManager.persist(product1);
		Product product2 = new Product(112, "realmi10",13000,"electronics","excellent",null,null);
		entityManager.persist(product2);
	}
	
	@Test
	public void testFindById() {
		Product product = productRepo.findById(105).get();
		assertEquals("hairOil",product.getName());
	}
	
	@Test
	public void findAll() {
		Iterable products = productRepo.findAll();
		//assertEquals("electronics",products.getCategory());
		assertThat(products).hasSize(5);
	}
	@Test
	public void findAllByCategory() {
		Product products = productRepo.findAllByCategory("electronics").get(0);
		assertEquals("electronics",products.getCategory());
		//assertThat(products).hasSize(2);
	}
	@Test
	public void findAllByName() {
		Product products = productRepo.findAllByName("hairOil").get(0);
		assertEquals("Beauty products",products.getCategory());	
	}
	
	@Test
	public void testsaveAll() {
		Product product1 =  new Product(1, "hairOil1", 700, "Beauty products1", "100% natural1",null,null);
		Product product2 =  new Product(2, "hairOil2", 800, "Beauty products", "100% natural",null,null);
		List<Product> products = new ArrayList<>();
		products.add(product2);
		products.add(product1);
		List<Product> products1 = productRepo.saveAll(products);
		//assertEquals(products,products1);
		assertThat(products).hasSize(2);
	}
	
	@Test
	public void testSave() {
		Product product =  productRepo.save(new Product(105, "hairOil", 800, "Beauty products", "100% natural",null,null));
		entityManager.persist(product);
		assertThat(product).hasFieldOrPropertyWithValue("name", "hairOil");
	    assertThat(product).hasFieldOrPropertyWithValue("price", 800);
	}
	
	@Test
	public void testDelete() {
		productRepo.deleteById(105);
		Product product = productRepo.findById(105).orElse(null);
		assertEquals(null,product);
	}

}
