package com.MRSISA2021_T15.dto;

public class DermatologistDTO extends UserDTO {

	private double rating;
	private Integer numOfRating;

	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public DermatologistDTO() {
		super();
	}	
}
