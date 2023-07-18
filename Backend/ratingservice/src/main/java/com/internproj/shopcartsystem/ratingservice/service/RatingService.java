package com.internproj.shopcartsystem.ratingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internproj.shopcartsystem.ratingservice.entities.Rating;
import com.internproj.shopcartsystem.ratingservice.repositories.RatingRepository;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;

	//rateTheProduct
	public Rating rateTheProduct(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	public List<Rating> viewRatingsByProdId(int productId) {
		return ratingRepository.findAllByProdId(productId);
	}
	
	public List<Rating> viewRatingsByUserId(int userId){
		return ratingRepository.findAllByUserId(userId);
	}
	
	public String updateRating(int id, String comment) {
		 Rating rate = ratingRepository.findById(id).orElse(null);
		 rate.setComment(comment);
		 ratingRepository.save(rate);
		return "Comment Updated";
	}
	
}
