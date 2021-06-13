package com.MRSISA2021_T15.dto;

public class PharmacyDTO {

	private Integer id;
	private String name, address, city, country, description;
	private Double rating;
	private Double appointmentPrice;
	private Integer numOfRating;
	
	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public double getAppointmentPrice() {
		return appointmentPrice;
	}

	public void setAppointmentPrice(double appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}
}
