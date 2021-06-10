package com.MRSISA2021_T15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.CanceledPharAppoinment;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.CanceledPharmaAppointmentRepository;

@Service
public class CanceledAppService {

	@Autowired
	CanceledPharmaAppointmentRepository canceledR;
	
	public List<CanceledPharAppoinment> getPatientAllCanceledApp(Patient p){
		ArrayList<CanceledPharAppoinment> returnList = new ArrayList<CanceledPharAppoinment>();
		
		List<CanceledPharAppoinment> list = canceledR.findAll();
		for(CanceledPharAppoinment app : list) {
			if(app.getPatient().getId() == p.getId()) {
				returnList.add(app);
			}
		}
		return returnList;
	}
}
