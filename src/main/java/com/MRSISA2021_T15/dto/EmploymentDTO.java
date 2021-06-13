package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Pharmacy;

public abstract class EmploymentDTO {

	private Integer id;
	private int start, end;
	private Pharmacy pharmacy;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}	
}
