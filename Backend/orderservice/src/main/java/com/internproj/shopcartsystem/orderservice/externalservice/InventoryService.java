package com.internproj.shopcartsystem.orderservice.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.internproj.shopcartsystem.orderservice.entities.Inventory;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryService {
	
	@PutMapping("/inventory/updateQuantity/{id}/{quantity}")
	public void updateQuantity(@PathVariable String id,@PathVariable int quantity);
	

}
