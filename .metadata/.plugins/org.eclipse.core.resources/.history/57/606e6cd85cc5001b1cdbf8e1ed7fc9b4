package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.ComplaintPharmacist;
import com.MRSISA2021_T15.model.ComplaintPharmacy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ComplaintRepository;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import com.MRSISA2021_T15.repository.PharmacySearchRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private PharmacySearchRepository pharmacyRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Dermatologist>findAllDermatologist(){
		return dermatologistRepository.getAllDermatologist();
	}
	
	public List<Pharmacist>findAllPharmacist(){
		return pharmacistRepository.getAllPharmacist();
	}
	
	public List<Pharmacy>findAllPharmacy(){
		return pharmacyRepository.findAll();
	}
	
	public void addDerComplaint(ComplaintDermatologist com) {
		complaintRepository.save(com);
		
	}
	
	public void addPhaComplaint(ComplaintPharmacist com) {
		complaintRepository.save(com);
	}
	
	public void addPharyComplaint(ComplaintPharmacy com) {
		complaintRepository.save(com);
	}
	
	
	public List<Complaint> findAll(){
		return (List<Complaint>) complaintRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Complaint> getComplaintsToRespond() {
		return complaintRepository.getComplaintsToRespond();
	}
	
	@Transactional(readOnly = true)
	public List<Complaint> getResponses() {
		return complaintRepository.findBySystemAdmin(((SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String sendResponse(Complaint response) {
		String message = "";
		SystemAdmin systemAdmin = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin systemAdminDb = (SystemAdmin) userRepository.findById(systemAdmin.getId()).get();
		if (systemAdminDb.getFirstLogin()) {
			message = "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			Complaint complaint = complaintRepository.findByIdPessimisticWrite(response.getId());
			if (complaint.getSystemAdmin() == null) {
				complaint.setResponse(response.getResponse());
				complaint.setSystemAdmin(systemAdminDb);
				complaintRepository.save(complaint);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
		        mailMessage.setTo(complaint.getPatient().getEmail());
		        mailMessage.setSubject("Response to complaint number " + complaint.getId());
		        mailMessage.setFrom(env.getProperty("spring.mail.username"));
		        mailMessage.setText("Your complaint: " + complaint.getText() + "\n\nResponse: " + complaint.getResponse() + "\n\nBest regards,\n" + complaint.getSystemAdmin().getName());
		        emailSenderService.sendEmail(mailMessage);
			} else {
				message = "This complaint has already been answered!";
			}
		}
		return message;
	}
}
