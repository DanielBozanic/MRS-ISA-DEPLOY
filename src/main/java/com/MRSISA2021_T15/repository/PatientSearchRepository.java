package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Appointment;

@Repository
public interface PatientSearchRepository extends AppointmentRepository {

	List<Appointment> findAllByOrderByStartDesc();
}
