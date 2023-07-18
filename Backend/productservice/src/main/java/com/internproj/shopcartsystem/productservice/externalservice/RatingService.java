package com.internproj.shopcartsystem.productservice.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.internproj.shopcartsystem.productservice.entities.Rating;
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@GetMapping("/rating/viewRatingsByProdId/{productId}")
	public List<Rating> viewRatingsByProdId(@PathVariable int productId) ;
	
	@PutMapping("/rating/updateRating/{id}/{comment}")
	public String  updateRating(@PathVariable int id,@PathVariable String comment) ;

}
