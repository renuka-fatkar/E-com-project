package com.internproj.shopcartsystem.ratingservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.internproj.shopcartsystem.ratingservice.entities.Rating;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class RatingRepositoryTest {

	@Autowired
	private RatingRepository repo; 
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setup() {
		Rating rating1 = new Rating(31, 21, 41, 5, "Excellent");
		entityManager.persist(rating1);
		Rating rating2 = new Rating(32, 21, 42, 3, "Excellent");
		entityManager.persist(rating2);
		Rating rating3 = new Rating(33, 21, 43, 4, "Excellenbt");
		entityManager.persist(rating3);
	}
	

	@Test
	public void testfindAllByProdId() {
		Rating rating = repo.findAllByProdId(41).get(0);
		assertEquals(31,rating.getRateId());
	}
	
	@Test
	public void testfindAllByUserId() {
		Rating rating = repo.findAllByUserId(21).get(0);
		assertEquals(31,rating.getRateId());
	}

	@Test
	public void testSave() {
		Rating rating =  repo.save(new Rating(31, 21, 41, 5, "Excellent"));
		entityManager.persist(rating);
		assertThat(rating).hasFieldOrPropertyWithValue("userId", 21);
	    assertThat(rating).hasFieldOrPropertyWithValue("prodId", 41);
	    assertThat(rating).hasFieldOrPropertyWithValue("rating", 5);
	    assertThat(rating).hasFieldOrPropertyWithValue("comment", "Excellent");
	}
	

}
