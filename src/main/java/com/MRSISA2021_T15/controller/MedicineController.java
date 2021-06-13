package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.MedicineDTO;
import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import com.MRSISA2021_T15.service.MedicineService;
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
	public ResponseEntity<String> addMedicine(@RequestBody MedicineDTO medicineDto) {
		var message = medicineService.addMedicine(medicineDto);
		var gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<>(gson.toJson("The medicine has been added successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
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
	

	@GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody Iterable<Medicine> getAllMedicine() {
		return medicineRepository.findAll();
	}

	@GetMapping(path="/{pharmacyId}/getMedicineFromPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public List<Medicine> getMedicineFromPharmacy(@PathVariable Integer pharmacyId){
		List<MedicinePharmacy> medicinePharmacies = medicinePharmacyRepository.findByPharmacyId(pharmacyId);
		List<Medicine> returnList = new ArrayList<>();
		for (MedicinePharmacy mp : medicinePharmacies)
			returnList.add(mp.getMedicine());
		return returnList;
	}

	@GetMapping(path="/{medicineId}/findArrayById", produces = MediaType.APPLICATION_JSON_VALUE)
  	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ArrayList<Optional<Medicine>> getMedicineArrayById(@PathVariable Integer medicineId){
		ArrayList<Optional<Medicine>> returnList = new ArrayList<>();
		returnList.add(medicineRepository.findById(medicineId));
		return returnList;
	}

	@GetMapping(path="/{string}/findByString", produces = MediaType.APPLICATION_JSON_VALUE)
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
	public ResponseEntity<Object> edit(@PathVariable Integer medicineId, @RequestBody MedicineDTO mDto) throws NotFoundException {
		var med = medicineRepository.findById(medicineId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
		med.setName(mDto.getName());
		med.setAdditionalComments(mDto.getAdditionalComments());
		med.setComposition((mDto.getComposition()));
		med.setForm(mDto.getForm());
		med.setManufacturer(mDto.getManufacturer());
		med.setMedicineCode(mDto.getMedicineCode());
		med.setMedicineType(mDto.getMedicineType());
		medicineRepository.save(med);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path="/{medicineId}/updateAdditionalComments")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity<Object> editAdditionalComments(@PathVariable Integer medicineId, @RequestBody MedicineDTO mDto) throws NotFoundException {
		var med = medicineRepository.findById(medicineId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
		med.setAdditionalComments(mDto.getAdditionalComments());
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