package com.MRSISA2021_T15.view;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.EmploymentDermatologist;

import java.util.Set;

public class DermatologistView {

    private Integer id;
    private Double rating;
    private String address, city, country, email, name, password, surname, username, phoneNumber;
    private Set<AppointmentDermatologist> appointments;
    private Set<EmploymentDermatologist> employments;
    private Set<Absence> absence;

    public DermatologistView() {

    }

    public DermatologistView(Integer id, String phoneNumber, Double rating, String address, String city, String country, String email, String name, String password, String surname, String username, Set<AppointmentDermatologist> appointments, Set<EmploymentDermatologist> employments, Set<Absence> absence) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.username = username;
        this.appointments = appointments;
        this.employments = employments;
        this.absence = absence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AppointmentDermatologist> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentDermatologist> appointments) {
        this.appointments = appointments;
    }

    public Set<EmploymentDermatologist> getEmployments() {
        return employments;
    }

    public void setEmployments(Set<EmploymentDermatologist> employments) {
        this.employments = employments;
    }

    public Set<Absence> getAbsence() {
        return absence;
    }

    public void setAbsence(Set<Absence> absence) {
        this.absence = absence;
    }

}
