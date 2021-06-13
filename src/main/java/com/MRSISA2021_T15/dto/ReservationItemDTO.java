package com.MRSISA2021_T15.dto;

import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Reservation;

public class ReservationItemDTO {

	private Integer id;
	MedicineQuantity medicine;
	Reservation reservation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MedicineQuantity getMedicine() {
		return medicine;
	}

	public void setMedicine(MedicineQuantity medicine) {
		this.medicine = medicine;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
}
