
package com.MRSISA2021_T15.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.MRSISA2021_T15.security.RestAuthenticationEntryPoint;
import com.MRSISA2021_T15.security.TokenAuthenticationFilter;
import com.MRSISA2021_T15.service.CustomUserDetailsService;
import com.MRSISA2021_T15.utils.Constants;
import com.MRSISA2021_T15.utils.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests().antMatchers("/auth/login").permitAll()
				.antMatchers("/auth/createFirstSystemAdmin").permitAll()
				.antMatchers("/registration/registerPatient").permitAll()
				.antMatchers("/registration/confirmAccount").permitAll()
				.antMatchers("/registration/registerSystemAdministrator").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/registration/registerPharmacyAdministrator").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/registration/registerSupplier").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/registration/registerDermatologist").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/medicine/addMedicine").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/medicine/{medicineId}/delete").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/medicine/getMedicineList").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/medicine/all").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/medicine/{medicineId}/findById").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/medicine/{string}/findByString").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/medicine/{medicineId}/update").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/medicine/getMedicineTypes").permitAll()
				.antMatchers("/medicine/getMedicineForms").permitAll()
				.antMatchers("/medicinePharmacy/getMedicinePharmacist/pharmacy={pharmacyId}start={start}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/medicinePharmacy/getMedicineDermatologist/pharmacy={pharmacyId}start={start}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/medicinePharmacy/getAllMedicinePharmacy").permitAll()
				.antMatchers("/medicinePharmacy/searchMedicineByName/{name}").permitAll()
				.antMatchers("/allergies/checkForAllergiesPharmacist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/allergies/checkForAllergiesDermatologist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/pharmacy/registerPharmacy").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/pharmacy/getPharmacies").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/suppliers/updateSupplierData").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getSupplierData").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/updatePassword").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getMedicineSupply").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/writeOffer").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getOrders").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getPurchaseOrdersMedicine/{purchaseOrderId}").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getOffersBySupplier").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getPendingOffersBySupplier").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/updateOffer").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/getAllMedicine").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/suppliers/updateMedicineStock").hasAuthority(Constants.ROLE_SUPPLIER_STRING)
				.antMatchers("/appointment_creation/pharmacist").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/appointment_creation/getPharmacist/id={id}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/appointment_creation/getDermatologist/id={id}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/setDonePharmacist").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/appointment_creation/setDoneDermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/endAppointmentPharmacist").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/appointment_creation/endAppointmentDermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/dermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/employmentsDermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/dermatologistPredefined/{appointmentId}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/appointment_creation/defineDermatologistAppointment").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/appointment_creation/getPredefinedDermatologistAppointments/{pharmacyId}").permitAll()
				.antMatchers("/absence/pharmacist").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/absence/dermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/calendar/calendarPharmacist").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/calendar/calendarPharmacistToday").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/calendar/calendarDermatologist/pharmacy={pharmacyId}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/calendar/calendarDermatologist").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/calendar/calendarDermatologistToday").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/calendar/calendarDermatologistPredefined").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/calendar/calendarDermatologistPredefined/pharmacy={pId}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/dermatologist/add").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/dermatologist/{dermatologistId}/delete").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/dermatologist/all").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/dermatologist/{dermatologistId}/findById").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/dermatologist/{string}/findByString").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/dermatologist/update").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/dermatologist/updatePassword").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/dermatologist/get").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/employment/getPharmacyEmployees/{pharmacyId}").permitAll()
				.antMatchers("/patients/searchPatient").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/changeData").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/updatePassword").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/subscribeToPharamacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/unsubscribeToPharamacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/getSubscribedPharmacies").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/sendQrCode").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/issueEReceipt").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patients/getDiscountByPatientCategory").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/patient-search/searchPatientsPharmacist/start={start}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/patient-search/searchPatientsDermatologist/start={start}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/patient-search/searchAllPharmacist/name={name}surname={surname}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/patient-search/searchAllDermatologist/name={name}surname={surname}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/patient-search/searchAppointmentsPharmacist/name={name}surname={surname}").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/patient-search/searchAppointmentsDermatologist/name={name}surname={surname}").hasAuthority(Constants.ROLE_DERMATOLOGIST_STRING)
				.antMatchers("/pharmacist/add").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/pharmacist/{pharmacistId}/delete").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/pharmacist/all").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/pharmacist/get").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/pharmacist/update").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/pharmacist/updatePassword").hasAuthority(Constants.ROLE_PHARMACIST_STRING)
				.antMatchers("/pharmacy/publicGetPharmacies").permitAll()
				.antMatchers("/pharmacy/getPharmacy").permitAll()
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/findById").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/update").hasAuthority(Constants.ROLE_PHARMACY_ADMIN_STRING)
				.antMatchers("/complaint/getAllDermatologists").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/getAllPharmacist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/getAllPharmacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/getComplaints").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/checkDermatologist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/checkPharmacist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/checkPharmacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/addComplaintToDermatologist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/addComplaintToPharmacist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/addComplaintToPharmacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/complaint/getComplaintsToRespond").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/complaint/getResponses").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/complaint/sendResponse").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/make_dermatologist_appointment/getAllPharamacies").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_dermatologist_appointment/send").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_dermatologist_appointment/getPatientDerApp").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_dermatologist_appointment/delete").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/loyaltyProgram/defineCategories").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/loyaltyProgram/getCategoryNames").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/loyaltyProgram/definePointsForAppointmentAndConsulation").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/medicinePharmacy/orderMedicine").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/systemAdmin/updatePassword").hasAuthority(Constants.ROLE_SYSTEM_ADMIN_STRING)
				.antMatchers("/make_farmaceut_appointment/getAllEmploymentPharmacist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/getAllAppointment").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/newAppointment").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/delete").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/getPatientPharApp").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/getAllCanceledApp").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/medicinePharmacy/getAllPatientsMedicines").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/medicinePharmacy/cancelMedicine").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/medicinePharmacy/getMedicineFromPharmacy/{pharmacyId}").permitAll()
				.antMatchers("/rating/getDermatologistToRate").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/getPharmacistsToRate").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/getPharmaciesToRate").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/getMedicineToRate").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/rateDermatologist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/ratePharmacist").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/ratePharmacy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/rateMedicine").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_dermatologist_appointment/getPastPatientDerApp").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/make_farmaceut_appointment/getPastPatientPharApp").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/pharmacySearch/getAll").permitAll()
				.antMatchers("/allergies/getAllMedicines").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/allergies/getAllAllergies").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/allergies/addAllergy").hasAuthority(Constants.ROLE_PATIENT_STRING)
				.antMatchers("/rating/getPenalties").hasAuthority(Constants.ROLE_PATIENT_STRING)
			.anyRequest().authenticated().and()
			.cors().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
