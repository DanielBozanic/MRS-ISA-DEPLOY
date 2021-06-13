package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.OrderedMedicineDTO;
import com.MRSISA2021_T15.dto.ReservationItemDTO;
import com.MRSISA2021_T15.model.MedicinePharmacy;
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
		List<MedicinePharmacy> retVal = new ArrayList<>();
		List<MedicinePharmacy> allMedicinePharmacy = (List<MedicinePharmacy>) repo.findAll();
		for (MedicinePharmacy mp: allMedicinePharmacy) {
			if (mp.getMedicine().getName().toLowerCase().contains(name.toLowerCase())) {
				retVal.add(mp);
			}
		}
		return retVal;
	}
	
	public void updateQuantity(OrderedMedicineDTO orderDto) {
		var mpb = repo.findUsingId(orderDto.getMedicinePharmacy().getId());
		var number = mpb.getAmount() - orderDto.getAmount();
		mpb.setAmount(number);
		repo.save(mpb);
	}
	
	public List<ReservationItem>getAllReservationItem(Patient p){
		var now = LocalDateTime.now();
		List<ReservationItem> returnList = new ArrayList<>();
		List<ReservationItem> list = (List<ReservationItem>) reservationRepo.findAll();
		for(ReservationItem ri: list) {
			if(p.getId().equals(ri.getReservation().getPatient().getId()) && now.compareTo(ri.getReservation().getEnd()) < 0 && ri.getReservation().getPickedUp() == null) {
				returnList.add(ri);
			}
		}
		return returnList;
	}
	
	
	public void deleteMedicine(ReservationItemDTO itemDto) {
		var pharmacyId = itemDto.getReservation().getPharmacy().getId();
		var medicineId = itemDto.getMedicine().getMedicine().getId();
		var mp = repo.findByExact(pharmacyId, medicineId);
		mp.setAmount(mp.getAmount() + itemDto.getMedicine().getQuantity());
		repo.save(mp);
		reservationRepo.deleteById(itemDto.getId());
		mr.deleteById(itemDto.getMedicine().getId());
		rr.deleteById(itemDto.getReservation().getId());
	}
}
