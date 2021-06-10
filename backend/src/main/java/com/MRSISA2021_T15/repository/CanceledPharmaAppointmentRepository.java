package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.CanceledPharAppoinment;


@Repository
public interface CanceledPharmaAppointmentRepository extends JpaRepository<CanceledPharAppoinment, Integer>{
	
	
	
}
