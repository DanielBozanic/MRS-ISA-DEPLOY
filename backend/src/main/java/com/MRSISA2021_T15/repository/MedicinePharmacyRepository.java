package com.MRSISA2021_T15.repository;
import com.MRSISA2021_T15.model.MedicinePharmacy;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedicinePharmacyRepository extends CrudRepository<MedicinePharmacy, Integer> {
	
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1")
	public List<MedicinePharmacy> findByPharmacyId(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1")
	public List<MedicinePharmacy> findByPharmacyIdPessimisticRead(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1")
	public List<MedicinePharmacy> findByPharmacyIdPessimisticW(Integer id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select m from MedicinePharmacy m where pharmacy.id = :id AND m.medicine.medicineCode in (:medicineCodes)")
	public List<MedicinePharmacy> findByPharmacyIdPessimisticWrite(Integer id, List<String> medicineCodes);
	
	

  @Query("select m from MedicinePharmacy m where pharmacy.id = ?1 and medicine.id = ?2")
	public MedicinePharmacy findByExact(Integer pharmacyId, Integer medicineId);
	

	@Query("select m from MedicinePharmacy m where m.id = ?1")
	public MedicinePharmacy findUsingId(Integer id);

	@Query("select mp from MedicinePharmacy mp where mp.pharmacy.id = ?1 AND mp.medicine.medicineCode = ?2 AND mp.amount >= ?3")
	public MedicinePharmacy getPharmacyByIdAndMedicineCode(Integer pharmacyId, String medicineCode, Integer quantity);

	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query("select mp from MedicinePharmacy mp where mp.pharmacy.id = ?1 AND mp.medicine.medicineCode = ?2 AND mp.amount >= ?3")
	public MedicinePharmacy getPharmacyByIdAndMedicineCodePessimisticRead(Integer pharmacyId, String medicineCode, Integer quantity);


}
