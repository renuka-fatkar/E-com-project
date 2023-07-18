package com.internproj.shopcartsystem.cartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internproj.shopcartsystem.cartservice.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	boolean findByProdName(String prodName);

}
