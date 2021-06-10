package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.OrderedMedicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;
import com.MRSISA2021_T15.repository.MedicineQuantityRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.ReservationRepository;

@Service
public class MedicinePharmacyService {

	@Autowired
	private MedicinePharmacyRepository repo;
	@Autowired
	private ReservationItemRepository reservationRepo;
	@Autowired
	private ReservationRepository rr;
	@Autowired
	private MedicineQuantityRepository mr;
	
	public List<MedicinePharmacy> medcineInPharmacy(Integer id) {
		return repo.findByPharmacyId(id);
	}
	
	public MedicinePharmacy medcineExact(Integer pharmacy, Integer medicine) {
		return repo.findByExact(pharmacy, medicine);
	}
	
	public List<MedicinePharmacy> getAllMedicinePharmacy() {
		return (List<MedicinePharmacy>) repo.findAll();
	}
	
	public List<MedicinePharmacy> searchMedicineByName(String name) {
		List<MedicinePharmacy> retVal = new ArrayList<MedicinePharmacy>();
		List<MedicinePharmacy> allMedicinePharmacy = (List<MedicinePharmacy>) repo.findAll();
		for (MedicinePharmacy mp: allMedicinePharmacy) {
			if (mp.getMedicine().getName().toLowerCase().contains(name.toLowerCase())) {
				retVal.add(mp);
			}
		}
		return retVal;
	}
	
	public void updateQuantity(OrderedMedicine order) {
		MedicinePharmacy mpb = repo.findUsingId(order.getMedicinePharmacy().getId());
		int number = mpb.getAmount() - order.getAmount();
		mpb.setAmount(number);
		repo.save(mpb);
	}
	
	public List<ReservationItem>getAllReservationItem(Patient p){
		LocalDateTime now = LocalDateTime.now();
		List<ReservationItem> returnList = new ArrayList<ReservationItem>();
		List<ReservationItem> list = (List<ReservationItem>) reservationRepo.findAll();
		for(ReservationItem ri: list) {
			if(p.getId() == ri.getReservation().getPatient().getId() && now.compareTo(ri.getReservation().getEnd()) < 0 && ri.getReservation().getPickedUp() == null) {
				returnList.add(ri);
			}
		}
		return returnList;
	}
	
	
	public void deleteMedicine(ReservationItem item) {
		Integer pharmacyId = item.getReservation().getPharmacy().getId();
		Integer medicineId = item.getMedicine().getMedicine().getId();
		
		
		
		MedicinePharmacy mp = repo.findByExact(pharmacyId, medicineId);
		System.out.println("123");
		System.out.println(medicineId);
		System.out.println(mp.getAmount() + item.getMedicine().getQuantity());
		
		mp.setAmount(mp.getAmount() + item.getMedicine().getQuantity());
		
		System.out.println(mp.getAmount());
		repo.save(mp);
		
		reservationRepo.deleteById(item.getId());
		mr.deleteById(item.getMedicine().getId());
		rr.deleteById(item.getReservation().getId());
		
		
	}
}
