package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Employment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentPharmacistsRepository extends CrudRepository<Employment, Integer> {
}
