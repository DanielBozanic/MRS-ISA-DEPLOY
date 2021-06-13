package com.MRSISA2021_T15.dto;

import java.time.LocalDateTime;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;

public abstract class AppointmentDTO {

	private Integer id;
	private LocalDateTime start, end;
	private double price, discount;
	private boolean done;
	private Patient patient;
	private Pharmacy pharmacy;
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
