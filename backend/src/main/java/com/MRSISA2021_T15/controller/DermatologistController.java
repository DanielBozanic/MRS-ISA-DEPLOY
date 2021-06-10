package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/dermatologist")
public class DermatologistController {
    @Autowired
    private DermatologistRepository dermatologistRepository;
    
    @Autowired
	private PasswordEncoder encod;
/*
    @RequestMapping(path="/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public @ResponseBody ResponseEntity addNewDermatologist (@RequestBody Dermatologist d) {

        d.setPassword(encod.encode(d.getPassword()));
        d.setEnabled(true);
        dermatologistRepository.save(d);
        return ResponseEntity.ok().build();
    }
*/
    @DeleteMapping(path = "/{dermatologistId}/delete")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public void deleteDermatologist(@PathVariable Integer dermatologistId) {
        dermatologistRepository.deleteById(dermatologistId);
    }

    @RequestMapping(path="/all")
    //@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public @ResponseBody Iterable<Dermatologist> getAllDermatologists() {
        return dermatologistRepository.findAll();
    }

    @RequestMapping(path="/{dermatologistId}/findArrayById")
    public ArrayList<Optional<Dermatologist>> getDermatologistArrayById(@PathVariable Integer dermatologistId) {
        ArrayList<Optional<Dermatologist>> returnList = new ArrayList<>();
        returnList.add(dermatologistRepository.findById(dermatologistId));
        return returnList;

    }
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
    public Optional<Dermatologist> getDermatologistById(@PathVariable Integer dermatologistId){
        return dermatologistRepository.findById(dermatologistId);
    }

    @RequestMapping(path="/{string}/findByString")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<Dermatologist> getDermatologistByString(@PathVariable String string){
        Iterable<Dermatologist> dermatologistList = dermatologistRepository.findAll();
        ArrayList<Dermatologist> returnList = new ArrayList<>();
        for(Dermatologist dermatologist: dermatologistList){
            if((dermatologist.getName() != null && dermatologist.getName().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getSurname() != null && dermatologist.getSurname().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getUsername() != null && dermatologist.getUsername().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getAddress() != null && dermatologist.getAddress().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getCity() != null && dermatologist.getCity().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getCountry() != null && dermatologist.getCountry().toLowerCase().contains(string.toLowerCase())||
                    (dermatologist.getEmail() != null && dermatologist.getEmail().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getPhoneNumber() != null && dermatologist.getPhoneNumber().contains(string.toLowerCase()))))
                returnList.add(dermatologist);
        }
    return returnList;
    }

    @PutMapping(value="/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public ResponseEntity<String> putDermatologist(@RequestBody Dermatologist d){
    	Gson gson = new GsonBuilder().create();
		Dermatologist pharm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pharm.setName(d.getName());
		pharm.setSurname(d.getSurname());
		pharm.setAddress(d.getAddress());
		pharm.setCity(d.getCity());
		pharm.setCountry(d.getCountry());
		pharm.setPhoneNumber(d.getPhoneNumber());
		dermatologistRepository.save(pharm);
		return new ResponseEntity<String>(gson.toJson("Update Succesfull!"), HttpStatus.OK);
	}
    
    @PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Gson gson = new GsonBuilder().create();
		if (p != null) {
			if (!encod.matches(passwords.getOldPassword(), p.getPassword())) {
				return new ResponseEntity<String>(gson.toJson("Wrong old password!"), HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				if (p.getFirstLogin()) {
					p.setFirstLogin(false);
				}
				p.setPassword(encod.encode(passwords.getPassword()));
				dermatologistRepository.save(p);
				return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>(gson.toJson("Password update unsuccessfull!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Optional<Dermatologist> getDermatologist(){
    	Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dermatologistRepository.findById(d.getId());
	}
}
