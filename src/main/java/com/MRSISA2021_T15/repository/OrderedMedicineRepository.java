package com.MRSISA2021_T15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.OrderedMedicine;

@Repository
public interface OrderedMedicineRepository extends JpaRepository<OrderedMedicine, Integer>{

}
