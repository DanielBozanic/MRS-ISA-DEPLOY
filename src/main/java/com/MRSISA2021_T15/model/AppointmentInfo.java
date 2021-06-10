package com.MRSISA2021_T15.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment_info")
public class AppointmentInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String comments;
	
	@ManyToOne
	private Appointment appointment;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "appointmentInfos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicineAppointment> medicineAppointment;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Set<MedicineAppointment> getMedicineAppointment() {
		return medicineAppointment;
	}

	public void setMedicineAppointment(Set<MedicineAppointment> medicineAppointment) {
		this.medicineAppointment = medicineAppointment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
