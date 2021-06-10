package com.MRSISA2021_T15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.MRSISA2021_T15.dto.ConfirmationToken;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.Role;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ConfirmationTokenRepository;
import com.MRSISA2021_T15.repository.RegistrationRepository;
import com.MRSISA2021_T15.repository.RoleRepository;
import com.MRSISA2021_T15.utils.Constants;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private EmailSenderService emailSenderService;

	@Transactional
	@Override
	public String registerPatient(Patient patient) {
		String message = "";
		if (registrationRepository.findByEmail(patient.getEmail().toLowerCase()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(patient.getUsername().toLowerCase()) != null) {
			message = "A user with this username already exists!";
		} else {
			patient.setUsername(patient.getUsername().toLowerCase());
            patient.setEmail(patient.getEmail().toLowerCase());
			patient.setPassword(passwordEncoder.encode(patient.getPassword()));
			patient.setEnabled(false);
			patient.setFirstLogin(false);
			patient.setCategoryName(CategoryName.REGULAR);
			patient.setCollectedPoints(0);
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_PATIENT).get();
			roles.add(role);
			patient.setRoles(roles);
			registrationRepository.save(patient);
			ConfirmationToken confirmationToken = new ConfirmationToken(patient);
			confirmationTokenRepository.save(confirmationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(patient.getEmail());
            mailMessage.setSubject("Verify account");
            mailMessage.setFrom(env.getProperty("spring.mail.username"));
            mailMessage.setText("To verify your account, please click here: "
            		+ "http://localhost:8080/registration/confirmAccount?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
		}
		return message;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            Patient patient = (Patient) registrationRepository.findByEmail(token.getUser().getEmail());
            patient.setEnabled(true);
            registrationRepository.save(patient); 
            modelAndView.setViewName("accountVerified");
        } else {
        	modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
	}

	@Override
	public String registerSystemAdmin(SystemAdmin systemAdmin) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			if (registrationRepository.findByEmail(systemAdmin.getEmail().toLowerCase()) != null) {
				message = "A user with this email already exists!";
			} else if (registrationRepository.findByUsername(systemAdmin.getUsername().toLowerCase()) != null) {
				message = "A user with this username already exists!";
			} else {
				systemAdmin.setUsername(systemAdmin.getUsername().toLowerCase());
				systemAdmin.setEmail(systemAdmin.getEmail().toLowerCase());
				systemAdmin.setPassword(passwordEncoder.encode(systemAdmin.getPassword()));
				systemAdmin.setEnabled(true);
				systemAdmin.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.findById(Constants.ROLE_SYSTEM_ADMIN).get();
				roles.add(role);
				systemAdmin.setRoles(roles);
				registrationRepository.save(systemAdmin);
			}
		}
		return message;
	}

	@Override
	public String registerDermatologist(Dermatologist dermatologist) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			if (registrationRepository.findByEmail(dermatologist.getEmail().toLowerCase()) != null) {
				message = "A user with this email already exists!";
			} else if (registrationRepository.findByUsername(dermatologist.getUsername().toLowerCase()) != null) {
				message = "A user with this username already exists!";
			} else {
				dermatologist.setUsername(dermatologist.getUsername().toLowerCase());
				dermatologist.setEmail(dermatologist.getEmail().toLowerCase());
				dermatologist.setPassword(passwordEncoder.encode(dermatologist.getPassword()));
				dermatologist.setEnabled(true);
				dermatologist.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.findById(Constants.ROLE_DERMATOLOGIST).get();
				roles.add(role);
				dermatologist.setRoles(roles);
				registrationRepository.save(dermatologist);
			}
		}
		return message;
	}

	@Override
	public String registerSupplier(Supplier supplier) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			if (registrationRepository.findByEmail(supplier.getEmail().toLowerCase()) != null) {
				message = "A user with this email already exists!";
			} else if (registrationRepository.findByUsername(supplier.getUsername().toLowerCase()) != null) {
				message = "A user with this username already exists!";
			} else {
				supplier.setUsername(supplier.getUsername().toLowerCase());
				supplier.setEmail(supplier.getEmail().toLowerCase());
				supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
				supplier.setEnabled(true);
				supplier.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.findById(Constants.ROLE_SUPPLIER).get();
				roles.add(role);
				supplier.setRoles(roles);
				registrationRepository.save(supplier);
			}
		}
		return message;
	}

	@Override
	public String registerPharmacyAdministrator(PharmacyAdmin pharmacyAdmin) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else {
			if (registrationRepository.findByEmail(pharmacyAdmin.getEmail().toLowerCase()) != null) {
				message = "A user with this email already exists!";
			} else if (registrationRepository.findByUsername(pharmacyAdmin.getUsername().toLowerCase()) != null) {
				message = "A user with this username already exists!";
			} else {
				pharmacyAdmin.setUsername(pharmacyAdmin.getUsername().toLowerCase());
				pharmacyAdmin.setEmail(pharmacyAdmin.getEmail().toLowerCase());
				pharmacyAdmin.setPassword(passwordEncoder.encode(pharmacyAdmin.getPassword()));
				pharmacyAdmin.setEnabled(true);
				pharmacyAdmin.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				Role role = roleRepository.findById(Constants.ROLE_PHARMACY_ADMIN).get();
				roles.add(role);
				pharmacyAdmin.setRoles(roles);
				registrationRepository.save(pharmacyAdmin);
			}
		}
		return message;
	}
}