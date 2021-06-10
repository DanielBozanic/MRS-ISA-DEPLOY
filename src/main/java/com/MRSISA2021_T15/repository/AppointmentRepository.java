package com.MRSISA2021_T15.repository;

import java.util.List;

import javax.persistence.LockModeType;


import com.MRSISA2021_T15.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query("select distinct p from Appointment a join a.patient p where  p.username like '?1%' and APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<Patient> findAllByUsernamePharmacist(String startsWith);
	
	@Query("select distinct p from Appointment a join a.patient p where APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<Patient> findAllPharmacist();
	
	
	
	
	@Query("select distinct p from Appointment a join a.patient p join a.pharmacist pa where p.id = ?1 and pa.id=?2 and APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<Patient> findAllPAwithPatientId(Integer id, Integer id2);
	
	@Query("select distinct p from Appointment a join a.patient p join a.pharmacist pa where p.id = ?1 and pa.id=?2 and APPOINTMENT_TYPE = 'APPOINTMENT_DERMATOLOGIST'")
	public List<Patient> findAllDAwithPatientId(Integer id, Integer id2);
	
	
	
	
	
	@Query("select distinct a from Appointment a where a.patient.id = null and APPOINTMENT_TYPE = 'APPOINTMENT_DERMATOLOGIST'")
	public List<AppointmentDermatologist>findAllFreeDermatologicalApp();
	
	@Query("select distinct a from Appointment a where a.patient.id = ?1 and APPOINTMENT_TYPE = 'APPOINTMENT_DERMATOLOGIST'")
	public List<AppointmentDermatologist> findAllDerAppWithPatientId(Integer id);
	
	
	@Query("select distinct a from Appointment a where a.patient.id = ?1 and APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<AppointmentPharmacist> findAllPharAppWithPatientId(Integer id);
	
	
	@Query("select distinct a from Appointment a where APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<AppointmentPharmacist> getAllPharmacistApp();
	
	
	@Query("select distinct p from Appointment a join a.patient p")
	public List<Patient> findPatients();
	
	@Query("select distinct p from Appointment a join a.patient p where a.pharmacist.id = ?1")
	public List<Patient> findPatientsPharmacist(Integer id);
	
	public List<Appointment> findAll();
	
	@Query("select a from Appointment a where a.pharmacist.id = ?1")
	public List<Appointment> findAllPharmacistId(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select a from Appointment a where a.pharmacist.id = ?1")
	public List<Appointment> findAllPharmacistIdPessimisticWrite(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select a from Appointment a where a.pharmacist.id = ?1")
	public List<Appointment> findAllPharmacistIdPessimisticRead(Integer id);
	
	@Query("select a from Appointment a where a.dermatologist.id = ?1")
	public List<Appointment> findAllDermatologistId(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select a from Appointment a where a.dermatologist.id = ?1")
	public List<Appointment> findAllDermatologistIdPessimisticWrite(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select a from Appointment a where a.dermatologist.id = ?1")
	public List<Appointment> findAllDermatologistIdPessimisticRead(Integer id);
	
	@Query("select a from Appointment a where a.patient.id = ?1")
	public List<Appointment> findAllPatientsId(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select a from Appointment a where a.patient.id = ?1")
	public List<Appointment> findAllPatientsIdPessimisticWrite(Integer id);
	
	@Query("select a from Appointment a where a.id = ?1")
	public Appointment findWithId(Integer id);


	@Query("select a from Appointment a where a.id = ?1")
	public AppointmentDermatologist findDerAppWithId(Integer id);


	
	
	@Query("select distinct d from Appointment a join a.dermatologist d where a.patient.id = ?1 and APPOINTMENT_TYPE = 'APPOINTMENT_DERMATOLOGIST' and done=true")
	public List<Dermatologist> findAllDoneDerAppOfPatient(Integer id);
	
	
	@Query("select distinct p from Appointment a join a.pharmacist p where a.patient.id = ?1 and APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST' and done=true")
	public List<Pharmacist> findAllDonePharAppOfPatient(Integer id);


	@Query("select distinct p from Appointment a join a.pharmacy p where a.patient.id = ?1 and done=true")
	public List<Pharmacy> findAllPharmaciesThatPatientHadApp(Integer id);
	
}
