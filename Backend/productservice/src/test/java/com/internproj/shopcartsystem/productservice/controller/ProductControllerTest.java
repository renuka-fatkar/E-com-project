package com.internproj.shopcartsystem.productservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.internproj.shopcartsystem.productservice.entities.Product;
import com.internproj.shopcartsystem.productservice.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	

	
	@BeforeEach
	void setUp() {
		Product product = new Product(107, "hairOil", 800, "Beauty products", "100% natural",null,null);
	}

	@Test
	public void testViewProductById() throws Exception {
		Product product = new Product(1, "hairOil", 800, "Beauty products", "100% natural",null,null);
		Mockito.when(service.viewProduct(1)).thenReturn(product);
		String uri = "/product/viewProduct/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		String expected =  new Gson().toJson(product);
		assertThat(outputjson.substring(0, 20)).isEqualTo(expected.substring(0,20));
	}

	@Test
	public void testViewByCategory() throws Exception {
		Product product1 = new Product(1, "hairOil1", 800, "Beauty products", "110% natural",null,null);
		Product product2 = new Product(2, "hairOil2", 900, "Beauty products", "100% natural",null,null);
		List<Product> products = new ArrayList<>();
		products.add(product2);
		products.add(product1);
		Mockito.when(service.viewProductsByCategory("Beauty products")).thenReturn(products);
		String uri = "/product/viewProducts/Beauty products";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		String expected =  new Gson().toJson(products);
		assertThat(outputjson.substring(0, 40)).isEqualTo(expected.substring(0,40));
	}
	@Test
	public void testViewByName() throws Exception {
		Product product1 = new Product(1, "hairOil", 800, "Beauty products1", "110% natural",null,null);
		Product product2 = new Product(2, "hairOil", 900, "Beauty products2", "100% natural",null,null);
		List<Product> products = new ArrayList<>();
		products.add(product2);
		products.add(product1);
		Mockito.when(service.viewProductsByName("hairOil")).thenReturn(products);
		String uri = "/product/viewProductsby/hairOil";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		String expected =  new Gson().toJson(products);
		assertThat(outputjson.substring(0, 40)).isEqualTo(expected.substring(0,40));
	}
	
	@Test
	public void testAddStudent() throws Exception {
		Product product = new Product(1, "hairOil", 800, "Beauty products", "100% natural",null,null);
		Mockito.when(service.addProduct(product)).thenReturn(product);
		mockMvc.perform(MockMvcRequestBuilders.post("/product/addProduct").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"+
						" \"productId\": 1,\r\n"+
						" \"name\": \"hairOil\",\r\n"+
						" \"price\": 800,\r\n"+
						" \"category\": \"Beauty products\",\r\n"+
						" \"specifications\": \"100% natural\"\r\n"+
						"}"
						)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testRemoveProduct() throws Exception {
		Mockito.when(service.removeProduct(101)).thenReturn("deleted");
		String uri = "/product/removeProduct/101";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		assertEquals("deleted",outputjson);
	}
}
