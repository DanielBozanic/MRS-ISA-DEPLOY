package com.MRSISA2021_T15.dto;

import java.util.ArrayList;

import com.MRSISA2021_T15.model.MedicineForm;
import com.MRSISA2021_T15.model.MedicineType;

public class MedicineDTO {

	private Integer id;
	private String medicineCode, name, composition, manufacturer;
	private String additionalComments;
	private MedicineType medicineType;
	private MedicineForm form;
	private Boolean prescription;
	private Integer averageRating;
	private Integer points;
	private Integer numOfRating;
	private ArrayList<Integer> substituteMedicineIds = new ArrayList<Integer>();
	
	
	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MedicineType getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(MedicineType medicineType) {
		this.medicineType = medicineType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public MedicineForm getForm() {
		return form;
	}

	public void setForm(MedicineForm form) {
		this.form = form;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String addtionalComments) {
		this.additionalComments = addtionalComments;
	}

	public Boolean getPrescription() {
		return prescription;
	}

	public void setPrescription(Boolean prescription) {
		this.prescription = prescription;
	}

	public ArrayList<Integer> getSubstituteMedicineIds() {
		return substituteMedicineIds;
	}

	public void setSubstituteMedicineIds(ArrayList<Integer> substituteMedicineIds) {
		this.substituteMedicineIds = substituteMedicineIds;
	}

	public Integer getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Integer averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
