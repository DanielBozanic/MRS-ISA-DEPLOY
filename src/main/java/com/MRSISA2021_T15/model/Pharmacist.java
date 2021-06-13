package com.MRSISA2021_T15.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "PHARMACIST")
public class Pharmacist extends User{

	private static final long serialVersionUID = 1L;

	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AppointmentPharmacist> appointments;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ComplaintPharmacist> complaints;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CanceledPharAppoinment> canceledAppointments;
	
	@Column
	private int numOfRating;
	
	public int getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(int numOfRating) {
		this.numOfRating = numOfRating;
	}

	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Absence> absence;
	@Column(name = "rating")
	private double rating;
	@OneToOne(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private EmploymentPharmacist employments;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<AppointmentPharmacist> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentPharmacist> appointments) {
		this.appointments = appointments;
	}

	public Pharmacist() {
		super();
	}
}
