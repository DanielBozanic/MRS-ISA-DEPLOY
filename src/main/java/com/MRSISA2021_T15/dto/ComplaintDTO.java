package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;

public class ComplaintDTO {

	private Integer id;
	private String text;
	private String response;
	private Patient patient;
	private SystemAdmin systemAdmin;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public SystemAdmin getSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(SystemAdmin systemAdmin) {
		this.systemAdmin = systemAdmin;
	}
}
