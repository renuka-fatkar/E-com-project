package com.internproj.shopcartsystem.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internproj.shopcartsystem.orderservice.entities.OrderDetails;
import com.internproj.shopcartsystem.orderservice.entities.TransactionDetails;
import com.internproj.shopcartsystem.orderservice.service.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	
	@GetMapping("/orderDetails")
	public String getOrderDetails() {
		return "OrderDetails";
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String> plcaeOrder(@RequestBody OrderDetails orderDetails) {
		String result = service.placeOrder(orderDetails);
		return ResponseEntity.ok("{\"data\": \"" + result + "\"}");

	}
	
	@DeleteMapping("/cancelOrder/{orderId}")
	public void cancelOrder(@PathVariable int orderId) {
		service.cancelOrder(orderId);
	}
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable Double amount) {
		return service.createTransaction(amount);
	}
	@GetMapping("/getOrdersByName/{userName}")
	public List<OrderDetails> getAllOrdersByUserName(@PathVariable String userName){
		return service.viewOrdersByUserName(userName);
	}

}
