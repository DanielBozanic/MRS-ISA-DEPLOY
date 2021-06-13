package com.MRSISA2021_T15.dto;

import java.time.LocalDateTime;

public class AbsenceDTO {

	private Integer id;
	private LocalDateTime start, end;
	private String description;
	private boolean approved;
	private UserDTO doctor;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public UserDTO getDoctor() {
		return doctor;
	}
	
	public void setDoctor(UserDTO doctor) {
		this.doctor = doctor;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
