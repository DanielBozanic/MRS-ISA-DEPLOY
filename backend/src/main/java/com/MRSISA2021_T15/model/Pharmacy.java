package com.MRSISA2021_T15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NonNull
	private String name, address, city, country, description;
	
	@Column
	private Double rating;
	
	@Column
	private Double appointmentPrice;
	
	@Column
	private Integer numOfRating;
	
	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Employment> employments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ComplaintPharmacy> complaints;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MedicinePharmacy> medicine;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MedicineNeeded> medicineNeeded;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();;

	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Reservation> reservations;
	
	@Transient
	private ArrayList<Integer> pharmacyAdminsIds = new ArrayList<Integer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Promotion> promotions;

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PurchaseOrder> purchaseOrder;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CanceledPharAppoinment> canceledAppointments;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Employment> getEmployments() {
		return employments;
	}

	public void setEmployments(Set<Employment> employments) {
		this.employments = employments;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public Set<MedicinePharmacy> getMedicine() {
		return medicine;
	}

	public void setMedicine(Set<MedicinePharmacy> medicine) {
		this.medicine = medicine;
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

	public Set<PharmacyAdmin> getPharmacyAdmins() {
		return pharmacyAdmins;
	}

	public void setPharmacyAdmins(Set<PharmacyAdmin> pharmacyAdmins) {
		this.pharmacyAdmins = pharmacyAdmins;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Set<PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrderMedicines(Set<PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public double getAppointmentPrice() {
		return appointmentPrice;
	}

	public void setAppointmentPrice(double appointmentPrice) {
		this.appointmentPrice = appointmentPrice;
	}
}
