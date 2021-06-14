package com.MRSISA2021_T15.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.MRSISA2021_T15.dto.MedicineDTO;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineForm;
import com.MRSISA2021_T15.model.MedicineType;
import com.MRSISA2021_T15.repository.MedicineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class MedicineServiceTest {
	
	@Mock
	private MedicineRepository medicineRepository;
	
	@Mock
	private Medicine medicineMock;
	
	@InjectMocks
	private MedicineServiceImpl medicineService;
	
	@Test
	void addMedicineTest() {
		var medicine = new Medicine();
		medicine.setAdditionalComments("");
		medicine.setComposition("");
		medicine.setForm(MedicineForm.CAPSULE);
		medicine.setManufacturer("");
		medicine.setMedicineType(MedicineType.ANALGESIC);
		medicine.setName("");
		medicine.setPoints(9);
		medicine.setSubstituteMedicineIds(null);
		medicine.setMedicineCode("s123");
		medicine.setPrescription(false);
		
		var medicineDto = new MedicineDTO();
		medicineDto.setAdditionalComments("");
		medicineDto.setComposition("");
		medicineDto.setForm(MedicineForm.CAPSULE);
		medicineDto.setManufacturer("");
		medicineDto.setMedicineType(MedicineType.ANALGESIC);
		medicineDto.setName("");
		medicineDto.setPoints(9);
		medicineDto.setSubstituteMedicineIds(null);
		medicineDto.setMedicineCode("s123");
		medicineDto.setPrescription(false);
		
		when(medicineRepository.findByMedicineCode(medicine.getMedicineCode())).thenReturn(medicineMock);
		
		var message = medicineService.addMedicineInDatabase(medicineDto);
		
		assertEquals("A medicine with this code already exists!", message);
	}
}
