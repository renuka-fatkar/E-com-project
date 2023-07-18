package com.internproj.shopcartsystem.ratingservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.internproj.shopcartsystem.ratingservice.entities.Rating;
import com.internproj.shopcartsystem.ratingservice.repositories.RatingRepository;

@SpringBootTest
class RatingServiceTest {

	@Autowired
	private RatingService service;
	
	@MockBean
	private RatingRepository repo;
	
	@BeforeEach
	void setup() {
		Rating rating = new Rating(31, 121, 101, 5, "Excellent");
		List<Rating> ratings = new ArrayList<>();
		ratings.add(rating);
		Mockito.when(repo.findAllByProdId(101)).thenReturn(ratings);
		Mockito.when(repo.findAllByUserId(121)).thenReturn(ratings);
	}
	
	
	@Test
	public void testviewRatingsByProdId() {
		assertEquals(1,service.viewRatingsByProdId(101).size());
	}
	
	@Test
	public void testviewRatingsByUserId() {
		assertEquals(1,service.viewRatingsByUserId(121).size());
	}
	
	@Test
	public void rateTheProduct() {
		
		Rating rating = new Rating(32, 121, 101, 5, "Excellent");
		Mockito.when(repo.save(rating)).thenReturn(rating);
		assertEquals(rating, service.rateTheProduct(rating));

		}

}
