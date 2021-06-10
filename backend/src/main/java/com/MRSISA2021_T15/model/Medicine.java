package com.MRSISA2021_T15.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "medicine")
@JsonIgnoreProperties(value= {"substituteMedicine"})
public class Medicine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NonNull
	private String medicineCode, name, composition, manufacturer;
	
	@Column
	private String additionalComments;
	
	@Column
	@NonNull
	private MedicineType medicineType;
	
	@Column
	@NonNull
	private MedicineForm form;
	
	@Column
	@NonNull
	private Boolean prescription;
	
	@Column
	private Integer averageRating;
	
	@Column
	@NonNull
	private Integer points;

	@Column
	private Integer numOfRating;
	
	
	public Integer getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(Integer numOfRating) {
		this.numOfRating = numOfRating;
	}

	@Transient
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SubstituteMedicine> substituteMedicine = new HashSet<SubstituteMedicine>();
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Allergy> allergies = new HashSet<Allergy>();
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MedicinePharmacy> medicinePharmacy;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicineSupply> medicineSupply;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MedicineQuantity> medicineQuantity;
	
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MedicineNeeded> medicineNeeded;
	
	@Transient
	private ArrayList<Integer> substituteMedicineIds = new ArrayList<Integer>();

	
	public Set<MedicinePharmacy> getMedicine() {
		return medicinePharmacy;
	}

	public void setMedicine(Set<MedicinePharmacy> medicine) {
		this.medicinePharmacy = medicine;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
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

	public Set<SubstituteMedicine> getSubstituteMedicine() {
		return substituteMedicine;
	}

	public void setSubstituteMedicine(Set<SubstituteMedicine> substituteMedicine) {
		this.substituteMedicine = substituteMedicine;
	}

	public ArrayList<Integer> getSubstituteMedicineIds() {
		return substituteMedicineIds;
	}

	public void setSubstituteMedicineIds(ArrayList<Integer> substituteMedicineIds) {
		this.substituteMedicineIds = substituteMedicineIds;
	}

	public Set<MedicineSupply> getMedicineSupply() {
		return medicineSupply;
	}

	public void setMedicineSupply(Set<MedicineSupply> medicineSupply) {
		this.medicineSupply = medicineSupply;
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
