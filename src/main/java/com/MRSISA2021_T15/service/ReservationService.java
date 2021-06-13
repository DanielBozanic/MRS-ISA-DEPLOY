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

import com.MRSISA2021_T15.dto.OrderedMedicineDTO;
import com.MRSISA2021_T15.dto.ReservationDTO;
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
	
	
	

	public List<List<Object>> getReservations(Integer id){
		List<List<Object>> list = new ArrayList<>();
		List<Object> medicines = new ArrayList<>();
		List<Object> reservations = new ArrayList<>();
		Optional<Reservation> reservation = resRepo.findById(id);
		if(reservation.isEmpty()) {
			return list;
		}
		var p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Employment employment = employRepo.findByPharmacistId(p.getId());
		
		if(!employment.getPharmacy().getId().equals(reservation.get().getPharmacy().getId())) {
			return list;
		}else if(reservation.get().getPickedUp() != null) {
			return list;
		} else if(LocalDateTime.now().isAfter(reservation.get().getEnd().minusDays(1))) {
			return list;
		}
		
		Iterable<ReservationItem> items = resiRepo.findAll();
		for (ReservationItem item : items) {
			if(item.getReservation().getId().equals(id)) {
				medicines.add(item.getMedicine());
			}
		}
		reservations.add(reservation.get());
		list.add(medicines);
		list.add(reservations);
		return list;
	}
	
	public String giveOut(ReservationDTO reservationDto) {
		var reservation = resRepo.findById(reservationDto.getId()).orElse(null);
		if (reservation != null) {
			reservation.setPickedUp(LocalDateTime.now());
			resRepo.save(reservation);
			var mailMessage = new SimpleMailMessage();
			mailMessage.setTo(reservation.getPatient().getEmail());
			mailMessage.setSubject("Successfull pickup");
			if (envir.getProperty("spring.mail.username") != null) {
				mailMessage.setFrom(envir.getProperty("spring.mail.username"));
			}
			mailMessage.setText("You have successfully picked up your medications contained in reservation with identifier " + reservation.getReservationId() 
			+ ". We hope you will visit us again at " + reservation.getPharmacy().getName() + ".");
			emailsS.sendEmail(mailMessage);
		}
		
		return "";
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void saveReservation(Patient patient, OrderedMedicineDTO orderDto) {
		var order = new OrderedMedicine();
		order.setAmount(orderDto.getAmount());
		order.setMedicinePharmacy(orderDto.getMedicinePharmacy());
		order.setUntil(orderDto.getUntil());
		var mq = new MedicineQuantity();
		mq.setMedicine(order.getMedicinePharmacy().getMedicine());
		mq.setQuantity(order.getAmount());
		medicineQuantityRepo.save(mq);
		
		var r = new Reservation();
		r.setPatient(patient);
		r.setPharmacy(order.getMedicinePharmacy().getPharmacy());
		r.setEnd(order.getUntil());
		
		
		var last = resRepo.findFirstByOrderByIdDesc();
		if(last == null) {
			r.setReservationId("Res1");
		}else {
			r.setReservationId("Res" + (last.getId() + 1));
		}
		
		
		r.setTotal(r.getTotal() + order.getMedicinePharmacy().getCost()* order.getAmount());
		ReservationItem ri = new ReservationItem();
		ri.setMedicine(mq);
		ri.setReservation(r);
		
		var patientDb = (Patient) userRepository.findById(patient.getId()).orElse(null);
		if (patientDb != null) {
			patient.setCollectedPoints(patient.getCollectedPoints() + 
					medicineRepository.getPointsByMedicineCode(order.getMedicinePharmacy().getMedicine().getMedicineCode()) * order.getAmount());
			if (patientDb.getCategoryName().equals(CategoryName.REGULAR)) {
				var c = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c.getRequiredNumberOfPoints())) {
					patientDb.setCategoryName(CategoryName.SILVER);
					r.setDiscount((100.0 - Math.abs(c.getDiscount())) / 100.0);
				} else {
					r.setDiscount(0.0);
				}
			} else if (patientDb.getCategoryName().equals(CategoryName.SILVER)) {
				var c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				var c2 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c1.getRequiredNumberOfPoints())) {
					patientDb.setCategoryName(CategoryName.GOLD);
					r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
				} else {
					r.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
				}
			} else if (patientDb.getCategoryName().equals(CategoryName.GOLD)) {
				var c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
			}
			userRepository.save(patientDb);
			resRepo.save(r);
			resiRepo.save(ri);
			orderMedRepo.save(order);
		}
	}
	
	
}
