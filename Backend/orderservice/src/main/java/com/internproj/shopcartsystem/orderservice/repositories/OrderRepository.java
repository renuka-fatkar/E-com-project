 package com.internproj.shopcartsystem.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internproj.shopcartsystem.orderservice.entities.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Integer>{

	List<OrderDetails> findAllByNameOfUser(String name);

}
