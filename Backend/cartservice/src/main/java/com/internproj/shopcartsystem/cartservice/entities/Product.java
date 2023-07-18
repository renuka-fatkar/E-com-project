package com.internproj.shopcartsystem.cartservice.entities;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private int productId;
	private String name;
	private int price;
	private int rating;
	private String specifications;
	

}
