package com.internproj.shopcartsystem.productservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internproj.shopcartsystem.productservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAllByCategory(String category);

	List<Product> findAllByName(String name);

}
