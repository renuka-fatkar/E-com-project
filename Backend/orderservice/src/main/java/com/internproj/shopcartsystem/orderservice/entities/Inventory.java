package com.internproj.shopcartsystem.orderservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	
	private int productId;
	private String name;
	private int price;
	private int rating;
	private String specifications;

}
