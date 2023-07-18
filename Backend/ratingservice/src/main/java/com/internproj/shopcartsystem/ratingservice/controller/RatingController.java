package com.internproj.shopcartsystem.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internproj.shopcartsystem.ratingservice.entities.Rating;
import com.internproj.shopcartsystem.ratingservice.service.RatingService;

@RestController
@RequestMapping("/rating")
@CrossOrigin("*")
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@PostMapping("/rateTheProduct")
	public Rating rateTheProduct(@RequestBody Rating rating) {
		return ratingService.rateTheProduct(rating);
	}
	@GetMapping("/viewRatingsByProdId/{productId}")
	public List<Rating> viewRatingsByProdId(@PathVariable int productId) {
		return ratingService.viewRatingsByProdId(productId);
	}
	@GetMapping("/viewRatingsByUserId/{userId}")
	public List<Rating> viewRatingsByUserId(@PathVariable int userId){
		return ratingService.viewRatingsByUserId(userId);
	}
	
	@PutMapping("/updateRating/{id}/{comment}")
	public String  updateRating(@PathVariable int id,@PathVariable String comment) {
		return ratingService.updateRating(id,comment);
	}
	

}
