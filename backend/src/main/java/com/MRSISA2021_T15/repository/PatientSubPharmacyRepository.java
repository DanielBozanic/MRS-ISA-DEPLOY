package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.model.Pharmacy;

@Repository
public interface PatientSubPharmacyRepository extends CrudRepository<PatientSubPharmacy, Integer> {
	
	@Query("select psp from PatientSubPharmacy psp where psp.pharmacy.id = ?1 AND psp.patient.id = ?2")
	PatientSubPharmacy findByPharmacyIdAndPatientId(Integer pharmacyId, Integer patientId);
	
	List<PatientSubPharmacy> findBySubscribedTrue();
	
	@Query("select psp.pharmacy from PatientSubPharmacy psp where psp.patient.id = ?1 AND psp.subscribed = TRUE")
	List<Pharmacy> getSubscribedPharmaciesForPatient(Integer patientId);
}
