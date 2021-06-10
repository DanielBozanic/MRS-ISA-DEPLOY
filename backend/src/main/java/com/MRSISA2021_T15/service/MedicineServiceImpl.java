package com.MRSISA2021_T15.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineForm;
import com.MRSISA2021_T15.model.MedicineType;
import com.MRSISA2021_T15.model.SubstituteMedicine;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.SubstituteMedicineRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private SubstituteMedicineRepository substituteMedicineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private MedicinePharmacyRepository medicinePharmacyRepository;

	@Transactional
	@Override
	public String addMedicine(Medicine medicine) {
		String message = "";
		SystemAdmin systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin systemAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).get();
		if (systemAdminDb.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			if (medicineRepository.findByMedicineCode(medicine.getMedicineCode().toLowerCase()) != null) {
				message = "A medicine with this code already exists!";
			} else {
				medicine.setMedicineCode(medicine.getMedicineCode().toLowerCase());
				medicine.setPoints(Math.abs(medicine.getPoints()));
				medicineRepository.save(medicine);
				List<Integer> substituteMedicineIds = medicine.getSubstituteMedicineIds();
				if (substituteMedicineIds != null) {
					for (Integer id : substituteMedicineIds) {
						SubstituteMedicine substituteMedicine = new SubstituteMedicine();
						substituteMedicine.setMedicine(medicine);
						Medicine sm = medicineRepository.findById(id).get();
						if (sm != null) {
							substituteMedicine.setSubstituteMedicine(sm);
							substituteMedicineRepository.save(substituteMedicine);
						}
					}
				}
			}
		}
		return message;
	}

	@Transactional(readOnly = true)
	@Override
	public HashMap<Integer, String> getMedicineList() {
		HashMap<Integer, String> medicineList = new HashMap<Integer, String>();
		Iterable<Medicine> medicine = medicineRepository.findAll();
		for (Medicine m : medicine) {
			medicineList.put(m.getId(), m.getName());
		}
		return medicineList;
	}

	@Override
	public HashSet<MedicineType> getMedicineTypes() {
		HashSet<MedicineType> medicineTypes = new HashSet<MedicineType>();
		for (MedicineType mt : MedicineType.values()) {
			medicineTypes.add(mt);
		}
		return medicineTypes;
	}

	@Override
	public HashSet<MedicineForm> getMedicineForms() {
		HashSet<MedicineForm> medicineForms = new HashSet<MedicineForm>();
		for (MedicineForm mf : MedicineForm.values()) {
			medicineForms.add(mf);
		}
		return medicineForms;
	}
	
	
	
}
