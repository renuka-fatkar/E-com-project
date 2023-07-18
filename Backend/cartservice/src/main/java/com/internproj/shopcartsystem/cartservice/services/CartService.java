package com.internproj.shopcartsystem.cartservice.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internproj.shopcartsystem.cartservice.entities.Cart;
import com.internproj.shopcartsystem.cartservice.entities.Product;
import com.internproj.shopcartsystem.cartservice.externalservices.ProductService;
import com.internproj.shopcartsystem.cartservice.repositories.CartRepository;
@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductService prodService;
	
	public String addToCart(Cart cart) {
		cartRepository.save(cart);
		return "added";
	}
	
	//removeFromCart
	public void deleteFromCart(int cartId) {
		cartRepository.deleteById(cartId);
	}
	
	//changeQuantity
	public void changeQuantity(int cartId, int quantity) {
		Cart cart = cartRepository.findById(cartId).orElse(null);
		cart.setQuantity(quantity);
	}
	
	public List<Cart> viewCart(){
		return cartRepository.findAll();
	}
	
	
}
