package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.PharmacyDTO;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.PharmacyRepository;
import com.MRSISA2021_T15.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public String registerPharmacy(PharmacyDTO pharmacyDto) {
		var systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var systeAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).orElse(null);
		if (systeAdminDb != null) {
			if (systeAdminDb.getFirstLogin()) {
				return "You are logging in for the first time, you must change password before you can use this functionality!";
			}  else {
				var pharmacy = new Pharmacy();
				pharmacy.setName(pharmacyDto.getName());
				pharmacy.setAddress(pharmacyDto.getAddress());
				pharmacy.setCity(pharmacyDto.getCity());
				pharmacy.setCountry(pharmacyDto.getCountry());
				pharmacy.setDescription(pharmacyDto.getDescription());
				pharmacy.setAppointmentPrice(0);
				pharmacy.setNumOfRating(0);
				pharmacy.setRating(0.0);
				pharmacyRepository.save(pharmacy);
			}
		}
		return "";
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pharmacy> getPharmacies() {
		return (List<Pharmacy>) pharmacyRepository.findAll();
	}

	@Override
	public Pharmacy getPharmacyData() {
		var pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(pharmacyAdmin);
		return pharmacyAdmin.getPharmacy();
	}

	@Override
	public String updatePharmacyData(PharmacyDTO pharmacyDto) {
		var message = "";
		var currentUser = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var currentUserDb = (PharmacyAdmin) userRepository.findById(currentUser.getId()).orElse(null);
		if (currentUserDb != null) {
			var updatedPharmacy = currentUserDb.getPharmacy();
			updatedPharmacy.setName(pharmacyDto.getName());
			updatedPharmacy.setAddress(pharmacyDto.getAddress());
			updatedPharmacy.setAppointmentPrice(pharmacyDto.getAppointmentPrice());
			updatedPharmacy.setCity(pharmacyDto.getCity());
			updatedPharmacy.setCountry(pharmacyDto.getCountry());
			updatedPharmacy.setDescription(pharmacyDto.getDescription());
			updatedPharmacy.setRating(pharmacyDto.getRating());
			pharmacyRepository.save(updatedPharmacy);
		}
		return message;
	}
}
