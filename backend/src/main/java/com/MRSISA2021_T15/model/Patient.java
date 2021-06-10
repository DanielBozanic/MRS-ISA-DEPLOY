package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "PATIENT")
public class Patient extends User{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Allergy> allergies = new HashSet<Allergy>();
	
	@JsonIgnore
	@OneToMany(mappedBy= "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint>complaints = new HashSet<Complaint>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservation;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EReceipt> eReceipts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CanceledPharAppoinment> canceledAppointments;
	
	@Column
	@NonNull
	private Integer collectedPoints;
	
	@Column
	@NonNull
	private CategoryName categoryName;
	
	@Column
	private int penals;
	

	
	
	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}
	
	/*public Patient(Set<Allergy> allergies) {
		super();
		this.allergies = allergies;
	}*/

	public Integer getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(Integer collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}
	
	public Set<EReceipt> geteReceipts() {
		return eReceipts;
	}

	public void seteReceipts(Set<EReceipt> eReceipts) {
		this.eReceipts = eReceipts;
	}

	public Patient() {
		super();
	}

	/*public Patient(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}*/

}
