package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.MRSISA2021_T15.dto.DermatologistDTO;
import com.MRSISA2021_T15.dto.PatientDTO;
import com.MRSISA2021_T15.dto.PharmacyAdminDTO;
import com.MRSISA2021_T15.dto.SupplierDTO;
import com.MRSISA2021_T15.dto.SystemAdminDTO;
import com.MRSISA2021_T15.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	private String success = "Registration successfull.";
	
	@PostMapping(value = "/registerPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody PatientDTO patientDto) {
		var message = registrationService.registerPatient(patientDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("Registration successfull. To log on you need to verify your email address."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/confirmAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		return registrationService.confirmAccount(modelAndView, confirmationToken);
    }
	
	@PostMapping(value = "/registerSystemAdministrator", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> registerSystemAdministrator(@RequestBody SystemAdminDTO systemAdminDto) {
		var message = registrationService.registerSystemAdmin(systemAdminDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(success), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/registerDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> registerDermatologist(@RequestBody DermatologistDTO dermatologistDto) {
		var message = registrationService.registerDermatologist(dermatologistDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(success), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/registerSupplier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> registerSupplier(@RequestBody SupplierDTO supplierDto) {
		var message = registrationService.registerSupplier(supplierDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(success), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/registerPharmacyAdministrator", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> registerPharmacyAdministrator(@RequestBody PharmacyAdminDTO pharmacyAdminDto) {
		var message = registrationService.registerPharmacyAdministrator(pharmacyAdminDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson(success), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
