package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.MedicineNeeded;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineNeededRepository extends CrudRepository<MedicineNeeded, Integer> {
    @Query("select m from MedicineNeeded m")
    public List<MedicineNeeded> findAllMedicineNeeded();
}
