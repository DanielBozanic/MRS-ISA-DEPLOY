package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.OrderedMedicine;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.CategoryRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.MedicineQuantityRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.OrderedMedicineRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.ReservationRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository resRepo;
	@Autowired
	ReservationItemRepository resiRepo;
	@Autowired
	EmploymentRepository employRepo;
	@Autowired
	EmailSenderService emailsS;
	@Autowired
	Environment envir;
	@Autowired
	MedicineQuantityRepository medicineQuantityRepo;
	@Autowired
	OrderedMedicineRepository orderMedRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MedicineRepository medicineRepository;
	
	
	

	public ArrayList<List<Object>> getReservations(Integer id){
		ArrayList<List<Object>> list = new ArrayList<>();
		ArrayList<Object> medicines = new ArrayList<>();
		ArrayList<Object> reservations = new ArrayList<>();
		Optional<Reservation> reservation = resRepo.findById(id);
		if(reservation.isEmpty()) {
			return list;
		}
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Employment employment = employRepo.findByPharmacistId(p.getId());
		
		if(employment.getPharmacy().getId() != reservation.get().getPharmacy().getId()) {
			return list;
		}else if(reservation.get().getPickedUp() != null) {
			return list;
		} else if(LocalDateTime.now().isAfter(reservation.get().getEnd().minusDays(1))) {
			return list;
		}
		
		Iterable<ReservationItem> items = resiRepo.findAll();
		for (ReservationItem item : items) {
			if(item.getReservation().getId() == id) {
				medicines.add(item.getMedicine());
			}
		}
		reservations.add(reservation.get());
		list.add(medicines);
		list.add(reservations);
		return list;
	}
	
	public String giveOut(Reservation reservation) {
		reservation.setPickedUp(LocalDateTime.now());
		resRepo.save(reservation);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(reservation.getPatient().getEmail());
		mailMessage.setSubject("Successfull pickup");
		mailMessage.setFrom(envir.getProperty("spring.mail.username"));
		mailMessage.setText("You have successfully picked up your medications contained in reservation with identifier " + reservation.getReservationId() 
		+ ". We hope you will visit us again at " + reservation.getPharmacy().getName() + ".");
		emailsS.sendEmail(mailMessage);
		
		return "";
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveReservation(Patient patient, OrderedMedicine order) {
		MedicineQuantity mq = new MedicineQuantity();
		mq.setMedicine(order.getMedicinePharmacy().getMedicine());
		mq.setQuantity(order.getAmount());
		medicineQuantityRepo.save(mq);
		
		Reservation r = new Reservation();
		r.setPatient(patient);
		r.setPharmacy(order.getMedicinePharmacy().getPharmacy());
		r.setEnd(order.getUntil());
		
		
		Reservation last = resRepo.findFirstByOrderByIdDesc();
		if(last == null) {
			r.setReservationId("Res1");
		}else {
			r.setReservationId("Res" + (last.getId() + 1));
		}
		
		
		r.setTotal(r.getTotal() + order.getMedicinePharmacy().getCost()* order.getAmount());
		ReservationItem ri = new ReservationItem();
		ri.setMedicine(mq);
		ri.setReservation(r);
		
		Patient patientDb = (Patient) userRepository.findById(patient.getId()).get();
		patient.setCollectedPoints(patient.getCollectedPoints() + 
				medicineRepository.getPointsByMedicineCode(order.getMedicinePharmacy().getMedicine().getMedicineCode()) * order.getAmount());
		if (patientDb.getCategoryName().equals(CategoryName.REGULAR)) {
			Category c = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
			if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c.getRequiredNumberOfPoints())) {
				patientDb.setCategoryName(CategoryName.SILVER);
				r.setDiscount((100.0 - Math.abs(c.getDiscount())) / 100.0);
			} else {
				r.setDiscount(0.0);
			}
		} else if (patientDb.getCategoryName().equals(CategoryName.SILVER)) {
			Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
			Category c2 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
			if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c1.getRequiredNumberOfPoints())) {
				patientDb.setCategoryName(CategoryName.GOLD);
				r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
			} else {
				r.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
			}
		} else if (patientDb.getCategoryName().equals(CategoryName.GOLD)) {
			Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
			r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
		}
		
		userRepository.save(patientDb);
		resRepo.save(r);
		resiRepo.save(ri);
		orderMedRepo.save(order);
	}
	
	
}
