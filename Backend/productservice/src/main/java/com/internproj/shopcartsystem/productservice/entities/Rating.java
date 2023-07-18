package com.internproj.shopcartsystem.productservice.entities;

public class Rating {
	
	private int rateId;
	private int userId;
	private int prodId;
	private int rating;
	private String comment;
	
	
	public Rating(int rateId, int userId, int prodId, int rating, String comment) {
		super();
		this.rateId = rateId;
		this.userId = userId;
		this.prodId = prodId;
		this.rating = rating;
		this.comment = comment;
	}


	public int getRateId() {
		return rateId;
	}


	public void setRateId(int rateId) {
		this.rateId = rateId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getProdId() {
		return prodId;
	}


	public void setProdId(int prodId) {
		this.prodId = prodId;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		return "Rating [rateId=" + rateId + ", userId=" + userId + ", prodId=" + prodId + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
	
	

}
