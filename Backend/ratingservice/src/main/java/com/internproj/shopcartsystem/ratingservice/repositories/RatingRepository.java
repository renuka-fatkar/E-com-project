package com.internproj.shopcartsystem.ratingservice.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internproj.shopcartsystem.ratingservice.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

	List<Rating> findAllByProdId(int productId);
	
	List<Rating> findAllByUserId(int userId);
	
	

}
