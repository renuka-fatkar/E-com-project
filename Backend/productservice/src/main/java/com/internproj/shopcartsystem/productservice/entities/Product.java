package com.internproj.shopcartsystem.productservice.entities;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue
	private int productId;
	private String name;
	private int price;
	private String category;
	private String specifications;
	private String img;
	
	@Transient
	private List<Rating> rating;
	

	
	


	
}