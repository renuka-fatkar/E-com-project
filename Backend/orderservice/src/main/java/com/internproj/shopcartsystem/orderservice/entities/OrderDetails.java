package com.internproj.shopcartsystem.orderservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
	
	@Id
	@GeneratedValue
	private int orderdetailId;
	private String nameOfUser;
	private String order_date;
	private String addressOfCust;
	private String phone;
	private String productId;
	private int quantity;
	private int amount;
	private String transactionId;
 

}
