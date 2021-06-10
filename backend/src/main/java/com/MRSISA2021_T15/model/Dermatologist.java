package com.MRSISA2021_T15.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "DERMATOLOGIST")
public class Dermatologist extends User{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AppointmentDermatologist> appointments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ComplaintDermatologist> complaints;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EmploymentDermatologist> employments;
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Absence> absence;

	@Column(name = "rating")
	private double rating;
	
	@Column
	private Integer numOfRating;


	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}

	public Set<AppointmentDermatologist> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentDermatologist> appointments) {
		this.appointments = appointments;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Dermatologist() {
		super();
	}	
}
