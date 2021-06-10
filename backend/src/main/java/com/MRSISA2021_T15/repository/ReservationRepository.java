package com.MRSISA2021_T15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	public Reservation findFirstByOrderByIdDesc();
}
