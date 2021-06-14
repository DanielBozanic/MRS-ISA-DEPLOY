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
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.dto.ComplaintDTO;
import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ComplaintRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ComplaintServiceTest {
	
	@Mock
	private UserRepository userRepositoryMock;
	
	@Mock
	private ComplaintRepository complaintRepositoryMock;
	
	@InjectMocks
	private ComplaintService complaintService;
	
	
	@Test
	@Transactional
    void sendResponseTest(){
		var systemAdmin = new SystemAdmin();
		systemAdmin.setId(14);
		
        var complaint = new Complaint();
        complaint.setId(1);
        complaint.setText("text");
        complaint.setResponse("some response");
        complaint.setSystemAdmin(systemAdmin);
        
        var complaintDto = new ComplaintDTO();
        complaintDto.setId(1);
        complaintDto.setText("text");
        complaintDto.setResponse("some response");
        complaintDto.setSystemAdmin(systemAdmin);
        
        when(complaintRepositoryMock.findByIdPessimisticWrite(complaint.getId()))
                .thenReturn(complaint);
        
        var message = complaintService.sendResponseToDatabase(complaintDto, systemAdmin);

        assertEquals("This complaint has already been answered!", message);
        
        verify(complaintRepositoryMock, times(1)).findByIdPessimisticWrite(complaint.getId());
    }
}
