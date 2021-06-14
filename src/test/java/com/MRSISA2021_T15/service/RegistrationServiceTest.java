package com.MRSISA2021_T15.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.MRSISA2021_T15.dto.PatientDTO;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.RegistrationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationServiceTest {
	
	@Mock
	private RegistrationRepository registrationRepositoryMock;
	
	@InjectMocks
	private RegistrationServiceImpl registrationService;
	
	@Test
	void registerPatientTest() {
		var patientDto = new PatientDTO();
		patientDto.setAddress("Bulevar neki");
		patientDto.setCity("NS");
		patientDto.setCountry("RS");
		patientDto.setName("CAR");
		patientDto.setPhoneNumber("32553352532");
		patientDto.setSurname("CARINA");
		patientDto.setUsername("CAR");
		patientDto.setEmail("CAR@hotmail.com");
		patientDto.setPassword("123");
		patientDto.setEnabled(false);
		patientDto.setFirstLogin(false);
		patientDto.setCategoryName(CategoryName.REGULAR);
		patientDto.setCollectedPoints(0);
		
		var patient = new Patient();
		patient.setAddress(patientDto.getAddress());
		patient.setCity(patientDto.getCity());
		patient.setCountry(patientDto.getCountry());
		patient.setName(patientDto.getName());
		patient.setPhoneNumber(patientDto.getPhoneNumber());
		patient.setSurname(patientDto.getSurname());
		patient.setUsername(patientDto.getUsername().toLowerCase());
        patient.setEmail(patientDto.getEmail().toLowerCase());
		patient.setPassword(patientDto.getPassword());
		patient.setEnabled(false);
		patient.setFirstLogin(false);
		patient.setCategoryName(CategoryName.REGULAR);
		patient.setCollectedPoints(0);
		
		when(registrationRepositoryMock.findByEmail(patient.getEmail()))
			.thenReturn(patient);
		
		var message = registrationService.registerPatient(patientDto);
		
		assertEquals("A user with this email already exists!", message);
		
		verify(registrationRepositoryMock, times(1)).findByEmail(patient.getEmail());
	}

}
