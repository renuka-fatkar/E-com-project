package com.internproj.shopcartsystem.inventoryservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internproj.shopcartsystem.inventoryservice.entities.Inventory;
@Repository
public interface InvventoryRepository extends JpaRepository<Inventory, Integer>{

	Inventory findByProductId(String id);

}
