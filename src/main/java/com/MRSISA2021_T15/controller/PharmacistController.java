package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.dto.PharmacistDTO;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pharmacist")
public class PharmacistController {
	@Autowired
	private PharmacistRepository pharmacistRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private PasswordEncoder encod;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody ResponseEntity<Object> addNewPharmacist(@RequestBody PharmacistDTO pDto) {
		var p = new Pharmacist();
		p.setAddress(pDto.getAddress());
		p.setCity(pDto.getCity());
		p.setCountry(pDto.getCountry());
		p.setEmail(pDto.getEmail().toLowerCase());
		p.setName(pDto.getName());
		p.setPhoneNumber(pDto.getPhoneNumber());
		p.setSurname(pDto.getSurname());
		p.setUsername(pDto.getUsername().toLowerCase());
		p.setPassword(encod.encode(pDto.getPassword()));
		p.setEnabled(true);
		pharmacistRepository.save(p);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{pharmacistId}/delete")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public void deletePharmacist(@PathVariable Integer pharmacistId) {
		pharmacistRepository.deleteById(pharmacistId);
	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody Iterable<Pharmacist> getAllPharmacists() {
		return pharmacistRepository.findAll();
	}

	@GetMapping(path = "/{pharmacistId}/findArrayById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Optional<Pharmacist>> getPharmacistArrayById(@PathVariable Integer pharmacistId) {
		ArrayList<Optional<Pharmacist>> returnList = new ArrayList<>();
		returnList.add(pharmacistRepository.findById(pharmacistId));
		return returnList;
	}

	@GetMapping(path = "/{string}/findByString", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Pharmacist> getPharmacistByString(@PathVariable String string) {
		Iterable<Pharmacist> pharmacistList = pharmacistRepository.findAll();
		ArrayList<Pharmacist> returnList = new ArrayList<>();
		for (Pharmacist pharmacist : pharmacistList) {
			if ((pharmacist.getName() != null && pharmacist.getName().toLowerCase().contains(string.toLowerCase()))
					|| (pharmacist.getSurname() != null
							&& pharmacist.getSurname().toLowerCase().contains(string.toLowerCase()))
					|| (pharmacist.getUsername() != null
							&& pharmacist.getUsername().toLowerCase().contains(string.toLowerCase()))
					|| (pharmacist.getAddress() != null
							&& pharmacist.getAddress().toLowerCase().contains(string.toLowerCase()))
					|| (pharmacist.getCity() != null
							&& pharmacist.getCity().toLowerCase().contains(string.toLowerCase()))
					|| (pharmacist.getCountry() != null
							&& pharmacist.getCountry().toLowerCase().contains(string.toLowerCase())
							|| (pharmacist.getEmail() != null
									&& pharmacist.getEmail().toLowerCase().contains(string.toLowerCase()))
							|| (pharmacist.getPhoneNumber() != null
									&& pharmacist.getPhoneNumber().contains(string.toLowerCase()))))
				returnList.add(pharmacist);
		}
		return returnList;
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Optional<Pharmacist> getPharmacist() {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return pharmacistRepository.findById(p.getId());
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public ResponseEntity<String> putPharmacist(@RequestBody PharmacistDTO pDto) {
		var gson = new GsonBuilder().create();
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pharm.setName(pDto.getName());
		pharm.setSurname(pDto.getSurname());
		pharm.setAddress(pDto.getAddress());
		pharm.setCity(pDto.getCity());
		pharm.setCountry(pDto.getCountry());
		pharm.setPhoneNumber(pDto.getPhoneNumber());
		pharmacistRepository.save(pharm);
		return new ResponseEntity<>(gson.toJson("Update Succesfull!"), HttpStatus.OK);
	}

	@PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var gson = new GsonBuilder().create();
		if (p != null) {
			if (!encoder.matches(passwords.getOldPassword(), p.getPassword())) {
				return new ResponseEntity<>(gson.toJson("Wrong old password!"), HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				if (p.getFirstLogin()) {
					p.setFirstLogin(false);
				}
				p.setPassword(encoder.encode(passwords.getPassword()));
				pharmacistRepository.save(p);
				return new ResponseEntity<>(gson.toJson(""), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(gson.toJson("Password update unsuccessfull!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
