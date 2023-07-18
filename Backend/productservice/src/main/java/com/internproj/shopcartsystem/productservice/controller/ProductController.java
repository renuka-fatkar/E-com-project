package com.internproj.shopcartsystem.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internproj.shopcartsystem.productservice.entities.Product;
import com.internproj.shopcartsystem.productservice.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/viewProduct/{prodId}")
	public Product viewProductById(@PathVariable int prodId) {
		return productService.viewProduct(prodId);
	}

	@GetMapping("/viewallProducts")
	public List<Product> viewAllProducts(
			@RequestParam(value = "pageNumber",required=false) Integer pageNumber,
			@RequestParam(value = "pageSize",required=false) Integer pageSize
			){
		return productService.viewAllProducts(pageNumber,pageSize );
	}
	@GetMapping("/viewProducts/{Category}")
		public List<Product> viewProductsByCategory(@PathVariable String Category){
			return productService.viewProductsByCategory(Category);
		}
		
		//viewbyname
		@GetMapping("/viewProductsby/{name}")
		public List<Product> viewProductsByName(@PathVariable String name){
			return productService.viewProductsByName(name);
		}
		
		//addProducts
		@PostMapping("/addProducts")
		public List<Product> addProducts(@RequestBody List<Product> products){
			return productService.addProducts(products);
		} 
		
		//addProduct
		@PostMapping("/addProduct")
		public Product addProduct(@RequestBody Product product){
			return productService.addProduct(product);
		}
		//removeProduct
		@DeleteMapping("/removeProduct/{productId}")
		public String removeProduct(@PathVariable int productId) {
			productService.removeProduct(productId);
			return "deleted";
		}
	
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	@PutMapping("/updateRating/{id}/{comment}")
	public String updateRating(@PathVariable int id,@PathVariable String comment) {
		return productService.upddateRating(id, comment);
	}
}
