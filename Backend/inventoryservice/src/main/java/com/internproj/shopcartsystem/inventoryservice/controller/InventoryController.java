package com.internproj.shopcartsystem.inventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internproj.shopcartsystem.inventoryservice.entities.Inventory;
import com.internproj.shopcartsystem.inventoryservice.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService service;

	@PostMapping("/addStock")
	public String addStock(@RequestBody Inventory inventory) {
		return service.addStock(inventory);
	}
	@DeleteMapping("/removeFromStock/{intId}")
	public String removeFromStock(@PathVariable int inId) {
		return service.removeFromStock(inId);
	}

	@PutMapping("/updateQuantity/{id}/{quantity}")
	public void updateQuantity(@PathVariable String id,@PathVariable int quantity) {
		service.updateQuantity(id,quantity);
	}
	

	
}
