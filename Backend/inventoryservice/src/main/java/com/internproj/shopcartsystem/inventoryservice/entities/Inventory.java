package com.internproj.shopcartsystem.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	@Id
	 private int inId;
	 private String productId;
	 private String productName;
	 private int instockQuantity;
	 private String pricePerItem;

}
