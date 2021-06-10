package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import com.MRSISA2021_T15.service.MedicineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private SubstituteMedicineRepository substituteMedicineRepository;

	@Autowired
	private AllergyRepository allergyRepository;
	@Autowired
	private MedicinePharmacyRepository medicinePharmacyRepository;
	@Autowired
	private MedicineNeededRepository medicineNeededRepository;



	@PostMapping(value = "/addMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
		String message = medicineService.addMedicine(medicine);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("The medicine has been added successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(path = "/{medicineId}/delete")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public void deleteMedicine(@PathVariable Integer medicineId) {

		for(SubstituteMedicine sm: substituteMedicineRepository.findAll())
		{
			if(sm.getMedicine() != null && sm.getMedicine().getId().equals( medicineId)) {
				sm.setMedicine(null);
				substituteMedicineRepository.save(sm);

			}
			if(sm.getSubstituteMedicine() != null && sm.getSubstituteMedicine().getId().equals(medicineId)){
				sm.setSubstituteMedicine(null);
				substituteMedicineRepository.save(sm);
		}
		}
		for(Allergy al: allergyRepository.findAll())
		{
			if(al.getMedicine() != null && al.getMedicine().getId().equals(medicineId)){
				al.setMedicine(null);
				allergyRepository.save(al);
			}
		}
		for(MedicinePharmacy mp: medicinePharmacyRepository.findAll()){
			if(mp.getMedicine() != null && mp.getMedicine().getId().equals(medicineId)){
				mp.setMedicine(null);

				medicinePharmacyRepository.save(mp);
			}
		}
		medicineRepository.deleteById(medicineId);
	}

	@GetMapping(value = "/getMedicineList", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public HashMap<Integer, String> getMedicineList() {
		return medicineService.getMedicineList();
	}
	

	@RequestMapping(path="/all")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody Iterable<Medicine> getAllMedicine() {
		return medicineRepository.findAll();
	}

	@RequestMapping(path="/{pharmacyId}/getMedicineFromPharmacy")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ArrayList<Medicine> getMedicineFromPharmacy(@PathVariable Integer pharmacyId){
		List<MedicinePharmacy> medicinePharmacies = medicinePharmacyRepository.findByPharmacyId(pharmacyId);
		ArrayList<Medicine> returnList = new ArrayList<>();
		for (MedicinePharmacy mp : medicinePharmacies)
			returnList.add(mp.getMedicine());
		return returnList;
	}

	@RequestMapping(path="/{medicineId}/findArrayById")
  	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ArrayList<Optional<Medicine>> getMedicineArrayById(@PathVariable Integer medicineId){
		ArrayList<Optional<Medicine>> returnList = new ArrayList<>();
		returnList.add(medicineRepository.findById(medicineId));
		return returnList;
	}

	@RequestMapping(path="/{string}/findByString")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ArrayList<Medicine> getMedicineByString(@PathVariable String string){
		Iterable<Medicine> medicineList = medicineRepository.findAll();
		ArrayList<Medicine> returnList = new ArrayList<>();
		for(Medicine medicine: medicineList){
			if((medicine.getName() != null && medicine.getName().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getMedicineCode() != null && medicine.getMedicineCode().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getManufacturer() != null && medicine.getManufacturer().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getMedicineType() != null && medicine.getMedicineType().toString().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getAdditionalComments() != null && medicine.getAdditionalComments().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getComposition() != null && medicine.getComposition().toLowerCase().contains(string.toLowerCase()))||
					(medicine.getForm() != null && medicine.getForm().toString().contains(string.toLowerCase())))
				returnList.add(medicine);
		}
		return returnList;
	}
	
	@PutMapping(path="/{medicineId}/update")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity edit(@PathVariable Integer medicineId, @RequestBody Medicine m) throws NotFoundException {
		Medicine med = medicineRepository.findById(medicineId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
		med.setName(m.getName());
		med.setAdditionalComments(m.getAdditionalComments());
		med.setComposition((m.getComposition()));
		med.setForm(m.getForm());
		med.setManufacturer(m.getManufacturer());
		med.setMedicineCode(m.getMedicineCode());
		med.setMedicineType(m.getMedicineType());
		medicineRepository.save(med);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path="/{medicineId}/updateAdditionalComments")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity editAdditionalComments(@PathVariable Integer medicineId, @RequestBody Medicine m) throws NotFoundException {
		Medicine med = medicineRepository.findById(medicineId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
		med.setAdditionalComments(m.getAdditionalComments());
		medicineRepository.save(med);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/getMedicineTypes", produces = MediaType.APPLICATION_JSON_VALUE)
	public HashSet<MedicineType> getMedicineTypes() {
		return medicineService.getMedicineTypes();
	}
	
	@GetMapping(value = "/getMedicineForms", produces = MediaType.APPLICATION_JSON_VALUE)
	public HashSet<MedicineForm> getMedicineForms() {
		return medicineService.getMedicineForms();
	}

	@GetMapping(value = "/getMedicineInquiries", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public List<MedicineNeeded> getMedicineInquiries() {
		return medicineNeededRepository.findAllMedicineNeeded();
	}

}