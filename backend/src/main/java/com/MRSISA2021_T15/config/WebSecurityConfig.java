
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
				.antMatchers("/registration/registerSystemAdministrator").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerPharmacyAdministrator").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerSupplier").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerDermatologist").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/addMedicine").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/{medicineId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/getMedicineList").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{medicineId}/findById").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{string}/findByString").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{medicineId}/update").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/getMedicineTypes").permitAll()
				.antMatchers("/medicine/getMedicineForms").permitAll()
				.antMatchers("/medicinePharmacy/getMedicinePharmacist/pharmacy={pharmacyId}start={start}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/medicinePharmacy/getMedicineDermatologist/pharmacy={pharmacyId}start={start}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/medicinePharmacy/getAllMedicinePharmacy").permitAll()
				.antMatchers("/medicinePharmacy/searchMedicineByName/{name}").permitAll()
				.antMatchers("/allergies/checkForAllergiesPharmacist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/allergies/checkForAllergiesDermatologist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/pharmacy/registerPharmacy").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/pharmacy/getPharmacies").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/suppliers/updateSupplierData").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getSupplierData").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/updatePassword").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getMedicineSupply").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/writeOffer").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getOrders").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getPurchaseOrdersMedicine/{purchaseOrderId}").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getOffersBySupplier").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getPendingOffersBySupplier").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/updateOffer").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getAllMedicine").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/updateMedicineStock").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/appointment_creation/pharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/appointment_creation/getPharmacist/id={id}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/appointment_creation/getDermatologist/id={id}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/setDonePharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/appointment_creation/setDoneDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/endAppointmentPharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/appointment_creation/endAppointmentDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/dermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/employmentsDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/dermatologistPredefined/{appointmentId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/defineDermatologistAppointment").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/appointment_creation/getPredefinedDermatologistAppointments/{pharmacyId}").permitAll()
				.antMatchers("/absence/pharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/absence/dermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarPharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/calendar/calendarPharmacistToday").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/calendar/calendarDermatologist/pharmacy={pharmacyId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologistToday").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologistPredefined").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologistPredefined/pharmacy={pId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/add").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/{dermatologistId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/{dermatologistId}/findById").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/{string}/findByString").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/update").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/updatePassword").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/get").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/employment/getPharmacyEmployees/{pharmacyId}").permitAll()
				.antMatchers("/patients/searchPatient").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/changeData").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/updatePassword").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/subscribeToPharamacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/unsubscribeToPharamacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/getSubscribedPharmacies").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/sendQrCode").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/issueEReceipt").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/getDiscountByPatientCategory").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patient-search/searchPatientsPharmacist/start={start}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/patient-search/searchPatientsDermatologist/start={start}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/patient-search/searchAllPharmacist/name={name}surname={surname}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/patient-search/searchAllDermatologist/name={name}surname={surname}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/patient-search/searchAppointmentsPharmacist/name={name}surname={surname}").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/patient-search/searchAppointmentsDermatologist/name={name}surname={surname}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/pharmacist/add").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/{pharmacistId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/get").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/pharmacist/update").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/pharmacist/updatePassword").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/pharmacy/publicGetPharmacies").permitAll()
				.antMatchers("/pharmacy/getPharmacy").permitAll()
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/findById").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/update").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/complaint/getAllDermatologists").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getAllPharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getAllPharmacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getComplaints").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkDermatologist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkPharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkPharmacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToDermatologist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToPharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToPharmacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getComplaintsToRespond").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/complaint/getResponses").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/complaint/sendResponse").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/make_dermatologist_appointment/getAllPharamacies").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_dermatologist_appointment/send").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_dermatologist_appointment/getPatientDerApp").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_dermatologist_appointment/delete").hasAuthority("ROLE_PATIENT")
				.antMatchers("/loyaltyProgram/defineCategories").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/loyaltyProgram/getCategoryNames").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/loyaltyProgram/definePointsForAppointmentAndConsulation").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicinePharmacy/orderMedicine").hasAuthority("ROLE_PATIENT")
				.antMatchers("/systemAdmin/updatePassword").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/make_farmaceut_appointment/getAllEmploymentPharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/getAllAppointment").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/newAppointment").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/delete").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/getPatientPharApp").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/getAllCanceledApp").hasAuthority("ROLE_PATIENT")
				.antMatchers("/medicinePharmacy/getAllPatientsMedicines").hasAuthority("ROLE_PATIENT")
				.antMatchers("/medicinePharmacy/cancelMedicine").hasAuthority("ROLE_PATIENT")
				.antMatchers("/medicinePharmacy/getMedicineFromPharmacy/{pharmacyId}").permitAll()
				.antMatchers("/rating/getDermatologistToRate").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/getPharmacistsToRate").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/getPharmaciesToRate").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/getMedicineToRate").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/rateDermatologist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/ratePharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/ratePharmacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/rateMedicine").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_dermatologist_appointment/getPastPatientDerApp").hasAuthority("ROLE_PATIENT")
				.antMatchers("/make_farmaceut_appointment/getPastPatientPharApp").hasAuthority("ROLE_PATIENT")
				.antMatchers("/pharmacySearch/getAll").permitAll()
				.antMatchers("/allergies/getAllMedicines").hasAuthority("ROLE_PATIENT")
				.antMatchers("/allergies/getAllAllergies").hasAuthority("ROLE_PATIENT")
				.antMatchers("/allergies/addAllergy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/rating/getPenalties").hasAuthority("ROLE_PATIENT")
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
