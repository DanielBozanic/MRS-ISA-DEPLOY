package com.MRSISA2021_T15.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "COMPLAINT_TYPE",
		discriminatorType = DiscriminatorType.STRING
)
@Table(name = "complaints")
public class Complaint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NonNull
	private String text;
	
	@Column
	private String response;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	@NonNull
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "system_admin_id")
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
