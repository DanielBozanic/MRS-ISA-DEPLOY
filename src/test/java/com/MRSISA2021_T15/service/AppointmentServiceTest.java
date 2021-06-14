package com.MRSISA2021_T15.service;


import java.time.LocalDateTime;
import java.util.ArrayList;


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
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AppointmentCreationRepository;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceTest {
	
	@Mock
	private AppointmentCreationRepository appointmentRepoMock;
	
	@Mock
	private EmploymentRepository employmentRepoMock;
	
	@Mock
	private AbsenceRepository absenceRepoMock;
	
	@InjectMocks
	private AppointmentService appointmentServiceMock;

	@Test
	@Transactional
	void makeAppointmentDermatologistTest() {
		var dermatologist = new Dermatologist();
		dermatologist.setAddress("Mornarska 6");
		dermatologist.setName("Lale");
		dermatologist.setSurname("Brale");
		dermatologist.setCity("LO");
		dermatologist.setCountry("Njemacka");
		dermatologist.setEmail("maki@baki.com");
		dermatologist.setId(50);
		dermatologist.setPassword("123");
		dermatologist.setPhoneNumber("0606096096");
		
		var patient = new Patient();
		patient.setAddress("Mornari 6");
		patient.setName("Laki");
		patient.setSurname("Braki");
		patient.setCity("LO");
		patient.setCountry("Njemacka");
		patient.setEmail("daki@daki.com");
		patient.setId(55);
		patient.setPassword("123");
		patient.setPhoneNumber("0606096096");
		
		var pharmacy = new Pharmacy();
		pharmacy.setAddress("nedje 5");
		pharmacy.setAppointmentPrice(2000);
		pharmacy.setCity("BG");
		pharmacy.setCountry("bugarska");
		pharmacy.setDescription("razbijaa");
		pharmacy.setId(5);
		pharmacy.setName("duleeee");
		
		var appointment1 = new AppointmentDermatologist();
		appointment1.setDermatologist(dermatologist);
		appointment1.setDone(false);
		appointment1.setId(100);
		appointment1.setPatient(patient);
		appointment1.setPharmacy(pharmacy);
		appointment1.setPrice(2000);
		appointment1.setStart(LocalDateTime.of(2100, 7, 10, 15, 10));
		appointment1.setEnd(LocalDateTime.of(2100, 7, 10, 16, 10));
		
		var appointment2 = new AppointmentDermatologist();
		appointment2.setDermatologist(dermatologist);
		appointment2.setDone(false);
		appointment2.setId(101);
		appointment2.setPatient(patient);
		appointment2.setPharmacy(pharmacy);
		appointment2.setPrice(2000);
		appointment2.setStart(LocalDateTime.of(2100, 7, 10, 15, 40));
		appointment2.setEnd(LocalDateTime.of(2100, 7, 10, 16, 20));
		
		ArrayList<Appointment> appointments = new ArrayList<>();
		appointments.add(appointment1);
		
		when(appointmentRepoMock.findAllDermatologistIdPessimisticWrite(50)).thenReturn(appointments);
		when(appointmentRepoMock.findAllPatientsIdPessimisticWrite(55)).thenReturn(new ArrayList<Appointment>());
		when(employmentRepoMock.findByDermatologistIdPessimisticRead(50)).thenReturn(new ArrayList<EmploymentDermatologist>());
		when(absenceRepoMock.findAll()).thenReturn(new ArrayList<Absence>());
		
		var message = appointmentServiceMock.makeAppointmentDermatologist(appointment2);
		assertEquals("This dermatologist has already an appointment planned at that time!", message);
		verify(appointmentRepoMock, times(1)).findAllDermatologistIdPessimisticWrite(50);
	}
}
