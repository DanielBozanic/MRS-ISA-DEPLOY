package com.MRSISA2021_T15.dto;

import java.time.LocalDateTime;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;

public class ReservationDTO {

	private Integer id;
	private String reservationId;
	private LocalDateTime end, pickedUp;
	private Patient patient;
	private Pharmacy pharmacy;
	private double total;
	private Double discount;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public LocalDateTime getPickedUp() {
		return pickedUp;
	}
	
	public void setPickedUp(LocalDateTime pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
