package com.internproj.shopcartsystem.inventoryservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internproj.shopcartsystem.inventoryservice.entities.Inventory;
import com.internproj.shopcartsystem.inventoryservice.repositories.InvventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	InvventoryRepository repo;
	
	public String addStock(Inventory inventory) {
		return "Added successfully";
	}
	
	public String removeFromStock(int inId) {
		repo.deleteById(inId);
		return "Deleted Successfully";
	}

	public void updateQuantity(String productId, int quantity) {
		String[] array = productId.split("_");
		for(String i: array) {
			Inventory inven = repo.findByProductId(i);
			inven.setInstockQuantity(inven.getInstockQuantity()-quantity);	
			repo.save(inven);
		}
		
	}
	
}
