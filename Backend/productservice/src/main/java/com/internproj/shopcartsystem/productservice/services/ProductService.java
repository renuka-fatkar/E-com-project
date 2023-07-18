package com.internproj.shopcartsystem.productservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.internproj.shopcartsystem.productservice.entities.Product;
import com.internproj.shopcartsystem.productservice.externalservice.RatingService;
import com.internproj.shopcartsystem.productservice.repositories.ProductRepository;
@Service
public class ProductService {
	
	@Autowired
	ProductRepository  productRepository;
	
	@Autowired
	RatingService ratingService;
	
	//viewProduct
	public Product viewProduct(int prodId) {	
		Product product = productRepository.findById(prodId).orElse(null);
		product.setRating(ratingService.viewRatingsByProdId(prodId));
		return product;		
	}
	
	public String upddateRating(int id, String comment){
		return ratingService.updateRating(id, comment);
	}
	
	//viewbycategory
	public List<Product> viewProductsByCategory(String Category){
		return productRepository.findAllByCategory(Category);
	}
	
	//viewbyname
	public List<Product> viewProductsByName(String name){
		return productRepository.findAllByName(name);
	}
	
	//addProducts
	public List<Product> addProducts(List<Product> products){
		return productRepository.saveAll(products);
	} 
	
	//addProduct
	public Product addProduct(Product product){
		return productRepository.save(product);
	}
	//removeProduct
	public String removeProduct(int productId) {
		productRepository.deleteById(productId);
		return "deleted";
	}
	
	//updateProduct
	public Product updateProduct(Product product) {
		Product product1 = productRepository.findById(product.getProductId()).orElse(null);
		if(!product.getName().equals(null)) {
			product1.setName(product.getName());
		}
		if(product.getPrice()!=0) {
			product.setPrice(product.getPrice());
		}
		if(!product.getCategory().equals(null)) {
			product.setCategory(product.getCategory());
		}
		if(!product.getSpecifications().equals(null)) {
			product.setSpecifications(product.getSpecifications());
		}
		productRepository.save(product);
		return product;
	}

	public List<Product> viewAllProducts(
			Integer pageNumber, Integer pageSize
			) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Product> pageProducts= productRepository.findAll(p);
		List<Product> listProducts= pageProducts.getContent();
		return listProducts;
	}

}
