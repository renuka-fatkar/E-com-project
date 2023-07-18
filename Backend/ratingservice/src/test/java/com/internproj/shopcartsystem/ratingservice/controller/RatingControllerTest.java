package com.internproj.shopcartsystem.ratingservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.internproj.shopcartsystem.ratingservice.entities.Rating;
import com.internproj.shopcartsystem.ratingservice.service.RatingService;

@WebMvcTest(RatingController.class)
class RatingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RatingService ratingService;
	@Test
	public void testRateTheProduct() throws Exception {
		Rating rating = new Rating(31, 21, 41, 5, "Excellent");
		Mockito.when(ratingService.rateTheProduct(rating)).thenReturn(rating);
		String uri = "/rating/rateTheProduct";
		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"+
						" \"rateId\": 31,\r\n"+
						" \"userId\": 21,\r\n"+
						" \"prodId\": 41,\r\n"+
						" \"rating\": 5,\r\n"+
						" \"comment\": \"Excellent\"\r\n"+
						"}"
						)).andExpect(MockMvcResultMatchers.status().isOk());
		}
	
	@Test
	public void testViewRatingsByProdId() throws Exception {
		Rating rating = new Rating(31, 21, 41, 5, "Excellent");
		List<Rating> ratings = new ArrayList<>();
		ratings.add(rating);
		Mockito.when(ratingService.viewRatingsByProdId(41)).thenReturn(ratings);
		String uri = "/rating/viewRatingsByProdId/41";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		String expected =  new Gson().toJson(ratings);
		assertThat(outputjson).isEqualTo(expected);
		//assertThat(outputjson.substring(0, 20)).isEqualTo(expected.substring(0,20));
	
	}
	@Test
	public void testViewRatingsByUserId() throws Exception  {
		Rating rating = new Rating(31, 21, 41, 5, "Excellent");
		List<Rating> ratings = new ArrayList<>();
		ratings.add(rating);
		Mockito.when(ratingService.viewRatingsByUserId(21)).thenReturn(ratings);
		String uri = "/rating/viewRatingsByUserId/21";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputjson = result.getResponse().getContentAsString();
		String expected =  new Gson().toJson(ratings);
		assertThat(outputjson).isEqualTo(expected);
		
	}
}
