package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.repository.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository repository;

	public List<Appointment> findAllPharmacist(Integer id) {
		return repository.findAllPharmacistId(id);
	}

	@Transactional(readOnly = true)
	public List<Appointment> findAllPharmacistToday(Integer id) {
		List<Appointment> appointments = repository.findAllPharmacistIdPessimisticRead(id);
		ArrayList<Appointment> todays = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusDay = now.plusDays(1);
		LocalDateTime tomorrow = LocalDateTime.of(nowPlusDay.getYear(), nowPlusDay.getMonth(),
				nowPlusDay.getDayOfMonth(), 0, 0);
		for (Appointment appointment : appointments) {
			if (!appointment.isDone()) {
				if (appointment.getEnd().isAfter(now.minusMinutes(15)) && appointment.getStart().isBefore(tomorrow)) {
					todays.add(appointment);
				}
			}
		}
		return todays;
	}

	public List<Appointment> findAllDermatologist(Integer id) {
		return repository.findAllDermatologistId(id);
	}

	@Transactional(readOnly = true)
	public List<Appointment> findAllDermatologistToday(Integer id) {
		List<Appointment> appointments = repository.findAllDermatologistIdPessimisticRead(id);
		ArrayList<Appointment> todays = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlusDay = now.plusDays(1);
		LocalDateTime tomorrow = LocalDateTime.of(nowPlusDay.getYear(), nowPlusDay.getMonth(),
				nowPlusDay.getDayOfMonth(), 0, 0);
		for (Appointment appointment : appointments) {
			if (!appointment.isDone()) {
				if (appointment.getEnd().isAfter(now.minusMinutes(15)) && appointment.getStart().isBefore(tomorrow)) {
					todays.add(appointment);
				}
			}
		}
		return todays;
	}

}
