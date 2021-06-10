package com.MRSISA2021_T15.service;

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
	public String registerPharmacy(Pharmacy pharmacy) {
		SystemAdmin systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin systeAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).get();
		if (systeAdminDb.getFirstLogin()) {
			return "You are logging in for the first time, you must change password before you can use this functionality!";
		}  else {
			pharmacyRepository.save(pharmacy);
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
		PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(pharmacyAdmin);
		return pharmacyAdmin.getPharmacy();
	}

	@Override
	public String updatePharmacyData(Pharmacy pharmacy) {
		String message = "";
		PharmacyAdmin currentUser = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Pharmacy updatedPharmacy = currentUser.getPharmacy();
		if (currentUser != null) {
			updatedPharmacy.setName(pharmacy.getName());
			updatedPharmacy.setAddress(pharmacy.getAddress());
			updatedPharmacy.setAppointmentPrice(pharmacy.getAppointmentPrice());
			updatedPharmacy.setCity(pharmacy.getCity());
			updatedPharmacy.setCountry(pharmacy.getCountry());
			updatedPharmacy.setDescription(pharmacy.getDescription());
			updatedPharmacy.setRating(pharmacy.getRating());
			pharmacyRepository.save(updatedPharmacy);
		} else {
			message = "Update unsuccessfull!";
		}
		return message;
	}
}
