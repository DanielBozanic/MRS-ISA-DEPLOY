package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.ReservationItem;

@Repository
public interface ReservationItemRepository extends CrudRepository<ReservationItem, Integer>{
	@Query("select distinct m from ReservationItem ri join ri.medicine.medicine m where ri.reservation.patient.id = ?1 and ri.reservation.pickedUp != null")
	public List<Medicine> findAllMedicinesThatPatientPic(Integer id);
	
	@Query("select distinct p from ReservationItem ri join ri.reservation.pharmacy p where ri.reservation.patient.id = ?1 and ri.reservation.pickedUp != null")
	public List<Pharmacy> findAllPharmaciesThatPatientPicMrd(Integer id);
	
	
	

}
