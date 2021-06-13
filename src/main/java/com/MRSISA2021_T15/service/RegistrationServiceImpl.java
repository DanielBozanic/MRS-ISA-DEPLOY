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
import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.PatientDTO;
import com.MRSISA2021_T15.dto.PharmacyAdminDTO;
import com.MRSISA2021_T15.dto.SupplierDTO;
import com.MRSISA2021_T15.dto.SystemAdminDTO;
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
	
	private String exists = "A user with this email already exists!";
	private String usernameExists = "A user with this username already exists!";
	private String login = "You are logging in for the first time, you must change password before you can use this functionality!";

	@Transactional
	@Override
	public String registerPatient(PatientDTO patientDto) {
		var message = "";
		if (registrationRepository.findByEmail(patientDto.getEmail().toLowerCase()) != null) {
			message = exists;
		} else if (registrationRepository.findByUsername(patientDto.getUsername().toLowerCase()) != null) {
			message = usernameExists;
		} else {
			var patient = new Patient();
			patient.setAddress(patientDto.getAddress());
			patient.setCity(patientDto.getCity());
			patient.setCountry(patientDto.getCountry());
			patient.setName(patientDto.getName());
			patient.setPhoneNumber(patientDto.getPhoneNumber());
			patient.setSurname(patientDto.getSurname());
			patient.setUsername(patientDto.getUsername().toLowerCase());
            patient.setEmail(patientDto.getEmail().toLowerCase());
			patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
			patient.setEnabled(false);
			patient.setFirstLogin(false);
			patient.setCategoryName(CategoryName.REGULAR);
			patient.setCollectedPoints(0);
			List<Role> roles = new ArrayList<Role>();
			var role = roleRepository.findById(Constants.ROLE_PATIENT).orElse(null);
			if (role != null) {
				roles.add(role);
				patient.setRoles(roles);
				registrationRepository.save(patient);
				var confirmationToken = new ConfirmationToken(patient);
				confirmationTokenRepository.save(confirmationToken);
				var mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(patient.getEmail());
	            mailMessage.setSubject("Verify account");
	            if (env.getProperty("spring.mail.username") != null) {
	            	mailMessage.setFrom(env.getProperty("spring.mail.username"));
	            }
	            mailMessage.setText("To verify your account, please click here: "
	            		+ "http://localhost:8080/registration/confirmAccount?token=" + confirmationToken.getConfirmationToken());
	            emailSenderService.sendEmail(mailMessage);
			}
		}
		return message;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken) {
		var token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            var patient = (Patient) registrationRepository.findByEmail(token.getUser().getEmail());
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
	public String registerSystemAdmin(SystemAdminDTO systemAdminDto) {
		var message = "";
		var currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message = login;
		} else {
			if (registrationRepository.findByEmail(systemAdminDto.getEmail().toLowerCase()) != null) {
				message = exists;
			} else if (registrationRepository.findByUsername(systemAdminDto.getUsername().toLowerCase()) != null) {
				message = usernameExists;
			} else {
				var systemAdmin = new SystemAdmin();
				systemAdmin.setAddress(systemAdminDto.getAddress());
				systemAdmin.setCity(systemAdminDto.getCity());
				systemAdmin.setCountry(systemAdminDto.getCountry());
				systemAdmin.setName(systemAdminDto.getName());
				systemAdmin.setPhoneNumber(systemAdminDto.getPhoneNumber());
				systemAdmin.setSurname(systemAdminDto.getSurname());
				systemAdmin.setUsername(systemAdminDto.getUsername().toLowerCase());
				systemAdmin.setEmail(systemAdminDto.getEmail().toLowerCase());
				systemAdmin.setPassword(passwordEncoder.encode(systemAdminDto.getPassword()));
				systemAdmin.setEnabled(true);
				systemAdmin.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				var role = roleRepository.findById(Constants.ROLE_SYSTEM_ADMIN).orElse(null);
				if (role != null) {
					roles.add(role);
					systemAdmin.setRoles(roles);
					registrationRepository.save(systemAdmin);
				}
			}
		}
		return message;
	}

	@Override
	public String registerDermatologist(DermatologistDTO dermatologistDto) {
		var message = "";
		var currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  login;
		} else {
			if (registrationRepository.findByEmail(dermatologistDto.getEmail().toLowerCase()) != null) {
				message = exists;
			} else if (registrationRepository.findByUsername(dermatologistDto.getUsername().toLowerCase()) != null) {
				message = usernameExists;
			} else {
				var dermatologist = new Dermatologist();
				dermatologist.setAddress(dermatologistDto.getAddress());
				dermatologist.setCity(dermatologistDto.getCity());
				dermatologist.setCountry(dermatologistDto.getCountry());
				dermatologist.setName(dermatologistDto.getName());
				dermatologist.setPhoneNumber(dermatologistDto.getPhoneNumber());
				dermatologist.setSurname(dermatologistDto.getSurname());
				dermatologist.setUsername(dermatologistDto.getUsername().toLowerCase());
				dermatologist.setEmail(dermatologistDto.getEmail().toLowerCase());
				dermatologist.setPassword(passwordEncoder.encode(dermatologistDto.getPassword()));
				dermatologist.setEnabled(true);
				dermatologist.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				var role = roleRepository.findById(Constants.ROLE_DERMATOLOGIST).orElse(null);
				if (role != null) {
					roles.add(role);
					dermatologist.setRoles(roles);
					registrationRepository.save(dermatologist);
				}
			}
		}
		return message;
	}

	@Override
	public String registerSupplier(SupplierDTO supplierDto) {
		var message = "";
		var currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  login;
		} else {
			if (registrationRepository.findByEmail(supplierDto.getEmail().toLowerCase()) != null) {
				message = exists;
			} else if (registrationRepository.findByUsername(supplierDto.getUsername().toLowerCase()) != null) {
				message = usernameExists;
			} else {
				var supplier = new Supplier();
				supplier.setAddress(supplierDto.getAddress());
				supplier.setCity(supplierDto.getCity());
				supplier.setCountry(supplierDto.getCountry());
				supplier.setName(supplierDto.getName());
				supplier.setPhoneNumber(supplierDto.getPhoneNumber());
				supplier.setSurname(supplierDto.getSurname());
				supplier.setUsername(supplierDto.getUsername().toLowerCase());
				supplier.setEmail(supplierDto.getEmail().toLowerCase());
				supplier.setPassword(passwordEncoder.encode(supplierDto.getPassword()));
				supplier.setEnabled(true);
				supplier.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				var role = roleRepository.findById(Constants.ROLE_SUPPLIER).orElse(null);
				if (role != null) {
					roles.add(role);
					supplier.setRoles(roles);
					registrationRepository.save(supplier);
				}
			}
		}
		return message;
	}

	@Override
	public String registerPharmacyAdministrator(PharmacyAdminDTO pharmacyAdminDto) {
		var message = "";
		var currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getFirstLogin()) {
			message =  login;
		} else {
			if (registrationRepository.findByEmail(pharmacyAdminDto.getEmail().toLowerCase()) != null) {
				message = exists;
			} else if (registrationRepository.findByUsername(pharmacyAdminDto.getUsername().toLowerCase()) != null) {
				message = usernameExists;
			} else {
				var pharmacyAdmin = new PharmacyAdmin();
				pharmacyAdmin.setAddress(pharmacyAdminDto.getAddress());
				pharmacyAdmin.setCity(pharmacyAdminDto.getCity());
				pharmacyAdmin.setCountry(pharmacyAdminDto.getCountry());
				pharmacyAdmin.setName(pharmacyAdminDto.getName());
				pharmacyAdmin.setPhoneNumber(pharmacyAdminDto.getPhoneNumber());
				pharmacyAdmin.setSurname(pharmacyAdminDto.getSurname());
				pharmacyAdmin.setPharmacy(pharmacyAdminDto.getPharmacy());
				pharmacyAdmin.setUsername(pharmacyAdminDto.getUsername().toLowerCase());
				pharmacyAdmin.setEmail(pharmacyAdminDto.getEmail().toLowerCase());
				pharmacyAdmin.setPassword(passwordEncoder.encode(pharmacyAdminDto.getPassword()));
				pharmacyAdmin.setEnabled(true);
				pharmacyAdmin.setFirstLogin(true);
				List<Role> roles = new ArrayList<Role>();
				var role = roleRepository.findById(Constants.ROLE_PHARMACY_ADMIN).orElse(null);
				if (role != null) {
					roles.add(role);
					pharmacyAdmin.setRoles(roles);
					registrationRepository.save(pharmacyAdmin);
				}
			}
		}
		return message;
	}
}