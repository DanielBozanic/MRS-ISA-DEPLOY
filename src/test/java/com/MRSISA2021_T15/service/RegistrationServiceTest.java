package com.MRSISA2021_T15.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private SystemAdmin systemAdminMock;

	@Test
	void registerSystemAdmin() {
		var systemAdmin = new SystemAdmin();
		systemAdmin.setAddress("");
		systemAdmin.setCity("");
		systemAdmin.setCountry("");
		systemAdmin.setName("");
		systemAdmin.setPhoneNumber("");
		systemAdmin.setSurname("");
		systemAdmin.setUsername("rane");
		systemAdmin.setEmail("");
		systemAdmin.setPassword("");
		systemAdmin.setEnabled(true);
		systemAdmin.setFirstLogin(true);
		
		when(userRepository.findByUsername(systemAdmin.getUsername())).thenReturn(systemAdminMock);
		
		assertEquals(systemAdmin.getUsername(), systemAdminMock.getUsername());
		
	}
	
	
}
