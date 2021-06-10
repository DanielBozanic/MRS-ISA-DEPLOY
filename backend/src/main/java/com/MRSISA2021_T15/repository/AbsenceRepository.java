package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MRSISA2021_T15.model.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Integer>{

	@Query("select a from Absence a where a.approved = true")
	public List<Absence> findAllApproved();
}
