package com.MRSISA2021_T15.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.MRSISA2021_T15.dto.ComplaintDTO;
import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ComplaintRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@SpringBootTest
public class ComplaintServiceTest {

	@Mock
    private ComplaintRepository complaintRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;
    
    @Mock
    private SystemAdmin systemAdminMock;
    
    @Mock
    private Patient patientMock;
    
    @InjectMocks
    private ComplaintService complaintService;
    
    @Test
    public void sendResponse() {
        Complaint complaint = new Complaint();
        complaint.setId(1);
        complaint.setResponse("some response");
        complaint.setPatient(patientMock);

        when(userRepositoryMock.findById(14))
        	.thenReturn(Optional.of(systemAdminMock));
        
        when(complaintRepositoryMock.findById(complaint.getId()))
        	.thenReturn((Optional.of(complaint)));
        
        ComplaintDTO complaintDto = new ComplaintDTO();
        complaintDto.setId(complaint.getId());
        complaintDto.setPatient(complaint.getPatient());
        complaintDto.setText(complaint.getText());
       
        var message = complaintService.sendResponse(complaintDto);
        
        verify(complaintRepositoryMock, times(1)).findById(complaint.getId());
        verify(userRepositoryMock, times(1)).findById(14);
        
        assertEquals(message, "Response has been sent successully.");
    }
}
