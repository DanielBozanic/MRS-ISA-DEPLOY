package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.AppointmentDermatologistDTO;
import com.MRSISA2021_T15.dto.AppointmentPharmacistDTO;
import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentCreationRepository repository;
	@Autowired
	private EmploymentRepository repo2;
	@Autowired
	private AbsenceRepository repo3;
	@Autowired
	private MedicinePharmacyRepository repo4;
	@Autowired
	private MedicineQuantityRepository repo5;
	@Autowired
	private MedicineAppointmentRepository repo6;
	@Autowired
	private AppointmentInfoRepository repo7;
	@Autowired
	private ReservationRepository repo8;
	@Autowired
	private ReservationItemRepository repo9;
	@Autowired
	EmailSenderService emailsend;
	@Autowired
	Environment en;
	
	private String time = "Start can't be after end!";
	private String past = "Can't schedule appointment into past!";
	
    @Autowired
    private CategoryRepository categoryRepository;
	@Autowired
	private AppointmentConsultationPointsRepository appointmentConsultationPointsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CanceledPharmaAppointmentRepository canceledRepository;
	@Autowired
	private AllergyRepository allergyrepo;
	@Autowired
	private MedicineRepository medicineRepository;

	public List<Appointment> findAllPharmacist(Integer id) {
		return repository.findAllPharmacistIdPessimisticWrite(id);
	}

	public List<Appointment> findAllDermatologist(Integer id) {
		return repository.findAllDermatologistIdPessimisticWrite(id);
	}

	public List<Appointment> findAllPatients(Integer id) {
		return repository.findAllPatientsIdPessimisticWrite(id);
	}

	public List<Appointment> findAllAppointments() {
		return repository.findAll();
	}

	public Optional<Appointment> findAllAppointmentsId(Integer id) {
		return repository.findById(id);
	}
	
	public List<EmploymentDermatologist> employmentsDermatologist(){
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repo2.findByDermatologistId(p.getId());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void makeTrue(Appointment appointment) {
		appointment.setDone(true);
		repository.save(appointment);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String makeAppointmentPharmacist(AppointmentPharmacist appointment) {
		
		if(appointment.getStart().isAfter(appointment.getEnd())) {
			return time;	
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return past;
		}
		
		String pharmacistAbsent = "This pharmacist is absent at that time!";
		String busy = "This pharmacist has already an appointment planned at that time!";
		String patientBusy = "This patient has already an appointment planned at that time!";
				
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllPharmacist(appointment.getPharmacist().getId());
		List<Absence> absences = repo3.findAll();
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var employment = repo2.findByPharmacistIdPessimisticRead(p.getId());

		if (appointment.getStart().getHour() < employment.getStart()
				|| appointment.getEnd().getHour() >= employment.getEnd()) {
			return "This pharmacist doesnt work at that time!";
		}

		for (Absence absence : absences) {
			if (absence.getDoctor().getId().equals(appointment.getPharmacist().getId())) {
				if (appointment.getStart().isEqual(absence.getStart())
						|| appointment.getStart().isEqual(absence.getEnd())) {
					return pharmacistAbsent;
				} else if (appointment.getEnd().isEqual(absence.getStart())
						|| appointment.getEnd().isEqual(absence.getEnd())) {
					return pharmacistAbsent;
				} else if (appointment.getStart().isAfter(absence.getStart())
						&& appointment.getStart().isBefore(absence.getEnd())) {
					return pharmacistAbsent;
				} else if (appointment.getEnd().isBefore(absence.getEnd())
						&& appointment.getEnd().isAfter(absence.getStart())) {
					return pharmacistAbsent;
				} else if (appointment.getStart().isBefore(absence.getStart())
						&& appointment.getEnd().isAfter(absence.getEnd())) {
					return pharmacistAbsent;
				}
			}
		}

		for (Appointment appointment2 : appointmentsPharmacist) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return busy;
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return busy;
			}
		}

		for (Appointment appointment2 : appointmentsPatient) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return patientBusy;
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return patientBusy;
			}
		}
		repository.save(appointment);
		sendEmailAppointment(appointment);
		return "";
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String makeAppointmentDermatologist(AppointmentDermatologist appointment) {
		
		if(appointment.getStart().isAfter(appointment.getEnd())) {
			return time;
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return past;
		}
		
		var dermatologistAbsent = "This dermatologist is absent at that time!";
		var busy = "This dermatologist has already an appointment planned at that time!";
		var patientBusy = "This patient has already an appointment planned at that time!";
		
		var appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		var appointmentsPharmacist = findAllDermatologist(appointment.getDermatologist().getId());
		var absences = repo3.findAll();

		for (EmploymentDermatologist employment : repo2.findByDermatologistIdPessimisticRead(appointment.getDermatologist().getId())) {
			if (employment.getPharmacy().getId().equals(appointment.getPharmacy().getId())) {
				if (appointment.getStart().getHour() < employment.getStart()
						|| appointment.getEnd().getHour() >= employment.getEnd()) {
					return "This dermatologist doesnt work at that time!";
				}
				break;
			}
		}

		for (Absence absence : absences) {
			if (absence.getDoctor().getId().equals(appointment.getDermatologist().getId())) {
				if (appointment.getStart().isEqual(absence.getStart())
						|| appointment.getStart().isEqual(absence.getEnd())) {
					return dermatologistAbsent;
				} else if (appointment.getEnd().isEqual(absence.getStart())
						|| appointment.getEnd().isEqual(absence.getEnd())) {
					return dermatologistAbsent;
				} else if (appointment.getStart().isAfter(absence.getStart())
						&& appointment.getStart().isBefore(absence.getEnd())) {
					return dermatologistAbsent;
				} else if (appointment.getEnd().isBefore(absence.getEnd())
						&& appointment.getEnd().isAfter(absence.getStart())) {
					return dermatologistAbsent;
				} else if (appointment.getStart().isBefore(absence.getStart())
						&& appointment.getEnd().isAfter(absence.getEnd())) {
					return dermatologistAbsent;
				}
			}
		}

		for (Appointment appointment2 : appointmentsPharmacist) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return busy;
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return busy;
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return busy;
			}
		}

		for (Appointment appointment2 : appointmentsPatient) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return patientBusy;
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return patientBusy;
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return patientBusy;
			}
		}
		repository.save(appointment);
		sendEmailAppointment(appointment);
		return "";
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String makeAppointmentDermatologistPredefined(Integer id, Patient patient) {
		var appointment = repository.findWithId(id);
		
		if (appointment == null) {
			return "This appointment doesnt exist!";
		}else if(appointment.getStart().isAfter(appointment.getEnd())) {
			return time;
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return past;
		}else if (appointment.getPatient() != null) {
			return "This appointment is already assigned";
		}
		appointment.setPatient(patient);
		repository.save(appointment); 
		sendEmailAppointment(appointment);
		return "";
	}

	public List<Appointment> findAllPatientsId(Integer id) {
		return repository.findAllPatientsId(id);
	}

	public List<Patient> findAllPAwithPatientId(Integer id, Integer id2) {
		return repository.findAllPAwithPatientId(id, id2);
	}

	public List<Patient> findAllDAwithPatientId(Integer id, Integer id2) {
		return repository.findAllDAwithPatientId(id, id2);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String endAppointment(Appointment appointment, MedicineQuantity[] meds, String comments) {
		var msg = "";
		
		var allergies =  allergyrepo.findAllPatientsPessimisticRead(appointment.getPatient().getId());
		var medicines = repo4.findByPharmacyIdPessimisticW(appointment.getPharmacy().getId());
		
		for (MedicineQuantity medicine : meds) {
			
			for (Allergy allergy : allergies) {
				if(allergy.getMedicine().getId().equals(medicine.getMedicine().getId())) {
					msg = "Patient is allergic to this medicine!";
					break;
				}
			}
			
			for (MedicinePharmacy medicinePharm : medicines) {

				if (medicine.getMedicine().getId().equals(medicinePharm.getMedicine().getId())) {
					if (medicine.getQuantity() > medicinePharm.getAmount()) {
						msg = msg + "Your pharmacy has only " + medicinePharm.getAmount() + " of "
								+ medicine.getMedicine().getName() + ". ";
					}
					break;
				}

			}
		}
		
		if (msg.equals("")) {
			var ai = new AppointmentInfo();
			var r = new Reservation();
			
			Patient patient = (Patient) userRepository.findById(appointment.getPatient().getId()).orElse(null);
			if (patient != null) {
				for (MedicineQuantity medicine : meds) {
					patient.setCollectedPoints(Math.abs(patient.getCollectedPoints()) + 
							(medicineRepository.getPointsByMedicineCode(medicine.getMedicine().getMedicineCode()) * Math.abs(medicine.getQuantity())));
				}
				if (appointment instanceof AppointmentDermatologist) {
					if (appointmentConsultationPointsRepository.getPointsByType("APPOINTMENT") != null) {
						patient.setCollectedPoints(patient.getCollectedPoints() + Math.abs(appointmentConsultationPointsRepository.getPointsByTypePessimisticWrite("APPOINTMENT")));
					}
				}
				else if (appointment instanceof AppointmentPharmacist) {
					if (appointmentConsultationPointsRepository.getPointsByType("CONSULTATION") != null) {
						patient.setCollectedPoints(patient.getCollectedPoints() + Math.abs(appointmentConsultationPointsRepository.getPointsByTypePessimisticWrite("CONSULTATION")));
					}
				}
				
				if (patient.getCategoryName().equals(CategoryName.REGULAR)) {
					var c = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
					if (patient.getCollectedPoints() >= Math.abs(c.getRequiredNumberOfPoints())) {
						patient.setCategoryName(CategoryName.SILVER);
						appointment.setDiscount((100.0 - Math.abs(c.getDiscount())) / 100.0);
						r.setDiscount((100.0 - Math.abs(c.getDiscount())) / 100.0);
					} else {
						appointment.setDiscount(0.0);
						r.setDiscount(0.0);
					}
				} else if (patient.getCategoryName().equals(CategoryName.SILVER)) {
					var c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
					var c2 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
					if (patient.getCollectedPoints() >= Math.abs(c1.getRequiredNumberOfPoints())) {
						patient.setCategoryName(CategoryName.GOLD);
						appointment.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
						r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
					} else {
						appointment.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
						r.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
					}
				} else if (patient.getCategoryName().equals(CategoryName.GOLD)) {
					var c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
					appointment.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
					r.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
				}
				
				appointment.setPatient(patient);
				appointmentRepository.save(appointment);
				userRepository.save(patient);
			}
			
			ai.setAppointment(appointment);
			ai.setComments(comments);
			repo7.save(ai);
			
			if(meds.length != 0) {
				r.setEnd(LocalDateTime.now().plusDays(30));
				r.setPatient(appointment.getPatient());
				r.setPharmacy(appointment.getPharmacy());
				r.setTotal(0);
				var last = repo8.findFirstByOrderByIdDesc();
				if(last == null) {
					r.setReservationId("Res1");
				}else {
					r.setReservationId("Res" + (last.getId() + 1));	
				}
				repo8.save(r);
				
				var mailMessage = new SimpleMailMessage();
				mailMessage.setTo(appointment.getPatient().getEmail());
				mailMessage.setSubject("Medication reservation");
				if (en.getProperty("spring.mail.username") != null) {
					mailMessage.setFrom(en.getProperty("spring.mail.username"));
				}
				mailMessage.setText("Medication has been reserved for you in pharmacy " + appointment.getPharmacy().getName() +  ". You can pick it up till one day before " 
				+ r.getEnd() + ". When you come pick it up, you will have to give the pharmacist this identifier " + r.getReservationId() + ". Have a nice day!");
				emailsend.sendEmail(mailMessage);
			}
			
			for (MedicineQuantity medicine : meds) {
				for (MedicinePharmacy medicinePharm : medicines) {
					if (medicine.getMedicine().getId().equals(medicinePharm.getMedicine().getId())) {
						medicinePharm.setAmount(medicinePharm.getAmount() - medicine.getQuantity());
						repo4.save(medicinePharm);
						r.setTotal(r.getTotal() + medicinePharm.getCost()*medicine.getQuantity());
						break;
					}

				}
				repo5.save(medicine);
				
				var ma = new MedicineAppointment();
				ma.setMedicine(medicine);
				ma.setAppointmentInfo(ai);
				repo6.save(ma);
				
				var ri = new ReservationItem();
				ri.setMedicine(medicine);
				ri.setReservation(r);
				repo9.save(ri);
			}
			repo8.save(r);
		}
		return msg;
	}
	

	
	public List<AppointmentDermatologist>findAllFreeDermatologicalApp(){
		var now = LocalDateTime.now();
		List<AppointmentDermatologist> returnList = new ArrayList<>();
		
		for(AppointmentDermatologist a : repository.findAllFreeDermatologicalApp()) {
			if(now.compareTo(a.getStart()) < 0) {
				returnList.add(a);
			}
		}
		return returnList;
		
	}
	
	
	public void saveDerApp(AppointmentDermatologist app) {
		repository.save(app);
	}
	
	
	public List<AppointmentDermatologist> findAllDerAppWithPatientId(Integer id){
		var now = LocalDateTime.now();
		List<AppointmentDermatologist> returnList = new ArrayList<>();
		
		for(AppointmentDermatologist a : repository.findAllDerAppWithPatientId(id)) {
			if(now.compareTo(a.getStart()) < 0) {
				returnList.add(a);
			}
		}
		
		return returnList;
	}
	
	
	
	public List<AppointmentDermatologist> findAllPastDerAppWithPatientId(Integer id){
		var now = LocalDateTime.now();
		List<AppointmentDermatologist> returnList = new ArrayList<>();
		
		for(AppointmentDermatologist a : repository.findAllDerAppWithPatientId(id)) {
			if(now.compareTo(a.getStart()) > 0) {
				returnList.add(a);
			}
		}
		
		return returnList;
	}
	
	
	
	public List<AppointmentPharmacist> findAllPharAppWithPatientId(Integer id){
		var now = LocalDateTime.now();
		List<AppointmentPharmacist> returnList = new ArrayList<>();
		
		for(AppointmentPharmacist a : repository.findAllPharAppWithPatientId(id)) {
			if(now.compareTo(a.getStart()) < 0) {
				returnList.add(a);
			}
		}
		return returnList;
	}
	
	
	
	public List<AppointmentPharmacist> findAllPastPharAppWithPatientId(Integer id){
		var now = LocalDateTime.now();
		List<AppointmentPharmacist> returnList = new ArrayList<>();
		
		for(AppointmentPharmacist a : repository.findAllPharAppWithPatientId(id)) {
			if(now.compareTo(a.getStart()) > 0) {
				returnList.add(a);
			}
		}
		return returnList;
	}
	

	public void sendEmailAppointment(Appointment appointment) {
		var mailMessage = new SimpleMailMessage();
		mailMessage.setTo(appointment.getPatient().getEmail());
		mailMessage.setSubject("New appointment scheduled");
		if (en.getProperty("spring.mail.username") != null) {
			mailMessage.setFrom(en.getProperty("spring.mail.username"));
		}
		mailMessage.setText("New appointment scheduled in pharmacy " + appointment.getPharmacy().getName() 
				+ ". It is scheduled to be from " + appointment.getStart() + " to " + appointment.getEnd() + ". Have a nice day!");
		emailsend.sendEmail(mailMessage);
	}
	
	
	public List<AppointmentPharmacist> getPharmacisApp() {
		return appointmentRepository.getAllPharmacistApp();
	}
	
	
	public void newPharmaciesApp(AppointmentPharmacist appoinment) {
		appoinment.setPrice(1000);
		appointmentRepository.save(appoinment);
		
	}
	
	
	public void deleteDermatologicalApp(AppointmentDermatologistDTO appointmentDto) {
		var app = appointmentRepository.findDerAppWithId(appointmentDto.getId());
		app.setPatient(null);
		appointmentRepository.save(app);
	}
	
	public void deletePharmaciestApp(AppointmentPharmacistDTO appointmentDto) {
		var app = appointmentRepository.findById(appointmentDto.getId()).orElse(null);
		
		if (app != null) {
			appointmentRepository.delete(app);
		}
		
		var canceled = new CanceledPharAppoinment();
		canceled.setPatient(appointmentDto.getPatient());
		canceled.setPharmacist(appointmentDto.getPharmacist());
		canceled.setPharmacy(appointmentDto.getPharmacy());
		canceled.setStart(appointmentDto.getStart());
		canceled.setEnd(appointmentDto.getEnd());
		
		canceledRepository.save(canceled);
		
	}



}
