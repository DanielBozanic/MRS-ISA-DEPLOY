package com.MRSISA2021_T15.dto;

public class PharmacistDTO extends UserDTO {

	private int numOfRating;
	private double rating;
	
	public int getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(int numOfRating) {
		this.numOfRating = numOfRating;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public PharmacistDTO() {
		super();
	}
}
