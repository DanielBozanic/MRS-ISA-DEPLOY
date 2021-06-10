package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.service.MedicinePharmacyService;
import com.MRSISA2021_T15.service.ReservationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicinePharmacy")
public class MedicinePharmacyController {
	@Autowired
	MedicinePharmacyService mService;
	
	@Autowired
	ReservationService reservationService;

	@GetMapping(value = "/getMedicinePharmacist/pharmacy={pharmacyId}start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public List<MedicineQuantity> getMedicinePharmacist(@PathVariable("pharmacyId") Integer id, @PathVariable("start") String start) {
		List<MedicinePharmacy> medicines = mService.medcineInPharmacy(id);
		ArrayList<MedicineQuantity> meds = new ArrayList<>();
		for (MedicinePharmacy medicine : medicines) {
			if(medicine.getMedicine().getName().startsWith(start)) {
				MedicineQuantity med = new MedicineQuantity();
				med.setMedicine(medicine.getMedicine());
				med.setQuantity(medicine.getAmount());
				meds.add(med);
			}
		}
		return meds;
	}
	
	@GetMapping(value = "/getMedicineDermatologist/pharmacy={pharmacyId}start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public List<MedicineQuantity> getMedicineDermatologist(@PathVariable("pharmacyId") Integer id, @PathVariable("start") String start) {
		List<MedicinePharmacy> medicines = mService.medcineInPharmacy(id);
		ArrayList<MedicineQuantity> meds = new ArrayList<>();
		for (MedicinePharmacy medicine : medicines) {
			if(medicine.getMedicine().getName().startsWith(start)) {
				MedicineQuantity med = new MedicineQuantity();
				med.setMedicine(medicine.getMedicine());
				med.setQuantity(medicine.getAmount());
				meds.add(med);
			}
		}
		return meds;
	}
	
	@GetMapping(value = "/getAllMedicinePharmacy")
	public List<MedicinePharmacy> getAllMedicinePharmacy() {
		return mService.getAllMedicinePharmacy();
	}
	
	@GetMapping(value = "/searchMedicineByName/{name}")
	public List<MedicinePharmacy> searchMedicineByName(@PathVariable String name) {
		return mService.searchMedicineByName(name);
	}
	

	
	@PutMapping(value = "/orderMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>orderMedicine(@RequestBody OrderedMedicine order){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String message = "";
		mService.updateQuantity(order);
		reservationService.saveReservation(patient, order);
		
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("You ordered your medicine. Thank you for purchase. :)"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping(value = "/getAllPatientsMedicines")
	public List<ReservationItem> getAllPatientsMedicines() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return mService.getAllReservationItem(patient);
	}
	
	
	
	@PutMapping(value = "/cancelMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>cancelMedicine(@RequestBody ReservationItem reservationItem){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String message = "";
		LocalDateTime now = LocalDateTime.now();
		if(now.getYear() == reservationItem.getReservation().getEnd().getYear()) {
			if(now.getMonthValue() == reservationItem.getReservation().getEnd().getMonthValue()) {
				if(now.getDayOfMonth() == reservationItem.getReservation().getEnd().getDayOfMonth()) {
					message = "You can't cancel your appointment under 24h before it's beggining!";
				}else if(now.getDayOfMonth() + 1 == reservationItem.getReservation().getEnd().getDayOfMonth()) { //ako je otkazujem dan prije
					//provjeri sate i minute onda
					if(now.getHour() > reservationItem.getReservation().getEnd().getHour()) {
						message = "You can't cancel your appointment under 24h before it's beggining!";
					}else if(now.getHour() == reservationItem.getReservation().getEnd().getHour()) {
						//ovdje provjeri minute
						if(now.getMinute() >  reservationItem.getReservation().getEnd().getMinute()) { //moze tacno 24 od pocetka da otkaze
							message = "You can't cancel your appointment under 24h before it's beggining!";
						}
					}
				}
			}
		}
		
		if(message.equals("")) {
			mService.deleteMedicine(reservationItem);
		}
		
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("You cancel your medicine. :)"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getMedicineFromPharmacy/{pharmacyId}")
	public List<MedicinePharmacy> getMedicineFromPharmacy(@PathVariable("pharmacyId") Integer id) {

		return mService.medcineInPharmacy(id);
	}
}
