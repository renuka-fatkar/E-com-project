package com.internproj.shopcartsystem.ratingservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Rating {
	@Id
	@GeneratedValue
	private int rateId;
	private int userId;
	private int prodId;
	private int rating;
	private String comment;
	 
	
}
