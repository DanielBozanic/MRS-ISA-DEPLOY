package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AppointmentAbsenceRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.UserRepository;


@Service
public class AbsenceService {
	@Autowired
	private EmploymentRepository emp;
	@Autowired
	private AbsenceRepository abs;
	@Autowired
	private AppointmentAbsenceRepository appoRepo;
	@Autowired
	private UserRepository us;
	@Autowired
	JavaMailSender emails;
	@Autowired
	Environment environment;

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String createAbsencePharmacist(Absence absence) throws InterruptedException {
		
		if(absence.getStart().isAfter(absence.getEnd())) {
			return "Start can't be after end!";	
		}else if(absence.getStart().isBefore(LocalDateTime.now()) || absence.getStart().isEqual(LocalDateTime.now())) {
			return "Can't schedule appointment into past!";
		}
		
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = appoRepo.findAllPharmacistIdPessimisticRead(p.getId());

		for (Appointment appointment : appointments) {
			if (absence.getStart().isBefore(appointment.getStart()) && absence.getEnd().isAfter(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			} else if (absence.getStart().isAfter(appointment.getStart())
					&& absence.getStart().isBefore(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			} else if (absence.getEnd().isAfter(appointment.getStart())
					&& absence.getEnd().isBefore(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			}
		}
		
		absence.setDoctor(p);
		absence.setApproved(false);
		abs.save(absence);
		
		Thread thread = new Thread() {
			public void run() {
				sendMailPharmacist(p, absence);
			}
		};
		thread.start();
		
		return "";
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String createAbsenceDermatologist(Absence absence) throws InterruptedException {
		
		if(absence.getStart().isAfter(absence.getEnd())) {
			return "Start can't be after end!";	
		}else if(absence.getStart().isBefore(LocalDateTime.now()) || absence.getStart().isEqual(LocalDateTime.now())) {
			return "Can't schedule appointment into past!";
		}
		
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = appoRepo.findAllDermatologistIdPessimisticRead(p.getId());

		for (Appointment appointment : appointments) {
			if(appointment.getPatient() != null) {
				if (absence.getStart().isBefore(appointment.getStart()) && absence.getEnd().isAfter(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				} else if (absence.getStart().isAfter(appointment.getStart())
						&& absence.getStart().isBefore(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				} else if (absence.getEnd().isAfter(appointment.getStart())
						&& absence.getEnd().isBefore(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				}
			}
		}
		
		absence.setDoctor(p);
		absence.setApproved(false);
		abs.save(absence);
		
		Thread thread = new Thread() {
			public void run() {
				sendMailDermatologist( p, absence);
			}
		};
		thread.start();

		return "";
	}
	
	public void sendMailPharmacist(Pharmacist p, Absence absence) {
		Employment employment = emp.findByPharmacistId(p.getId());
		for (PharmacyAdmin pharmacyAdmin : us.findAllPharmacyAdmins()) {
			if(pharmacyAdmin.getPharmacy().getId() == employment.getPharmacy().getId()) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(pharmacyAdmin.getEmail());
				mailMessage.setSubject("New absence request");
				mailMessage.setFrom(environment.getProperty("spring.mail.username"));
				mailMessage.setText("New absence request from pharmacist " + p.getName() + " " + p.getSurname() 
				+ ". The pharmacist wishes to be absent from " + absence.getStart() + " to " +  absence.getEnd() 
				+ ". The description of the reason he wnats to be absent is:\n" + absence.getDescription()
				+ ". \nPlease approve it or reject it.");
				emails.send(mailMessage);
				//Thread.sleep(3000);
			}
		}
	}
	
	public void sendMailDermatologist(Dermatologist p, Absence absence) {
		for (SystemAdmin systemAdmin : us.findAllSystemAdmins()) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(systemAdmin.getEmail());
			mailMessage.setSubject("New absence request");
			mailMessage.setFrom(environment.getProperty("spring.mail.username"));
			mailMessage.setText("New absence request from dermatologist " + p.getName() + " " + p.getSurname() 
			+ ". The dermatologist wishes to be absent from " + absence.getStart() + " to " +  absence.getEnd() 
			+ ". The description of the reason he wnats to be absent is:\n" + absence.getDescription()
			+". \nPlease approve it or reject it.");
			emails.send(mailMessage);
			//Thread.sleep(3000);
		}
	}
}
