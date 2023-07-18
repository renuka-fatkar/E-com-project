package com.internproj.shopcartsystem.cartservice.controller;


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
import org.springframework.web.bind.annotation.RestController;

import com.internproj.shopcartsystem.cartservice.entities.Cart;
import com.internproj.shopcartsystem.cartservice.services.CartService;
@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestBody Cart cart) {
		return cartService.addToCart(cart);
	}
	@DeleteMapping("/removeFromCart/{cartId}")
	public void deleteFromCart(@PathVariable int cartId) {
		cartService.deleteFromCart(cartId);
	}
	
	@PutMapping("/changeQuantity/{cartId}/{quantity}")
	public void changeQuantity(@PathVariable int cartId,@PathVariable int quantity) {
		cartService.changeQuantity(cartId, quantity);
	}
	
	@GetMapping("/viewCart")
	public List<Cart> viewCart(){
		return cartService.viewCart();
	}

}
