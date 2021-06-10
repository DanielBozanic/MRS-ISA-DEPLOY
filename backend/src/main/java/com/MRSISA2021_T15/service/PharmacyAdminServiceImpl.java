package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PharmacyAdmin getPharmacyAdminData() {
        return (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String updatePassword(ChangePassword passwords) {
        String message = "";
        PharmacyAdmin currentUser = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
                message = "Wrong old password!";
            } else {
                currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
                userRepository.save(currentUser);
            }
        } else {
            message = "Password update unsuccessfull!";
        }
        return message;
    }

    @Override
    public String updatePharmacyAdminData(PharmacyAdmin pharmacyAdmin) {
        String message = "";
        PharmacyAdmin currentUser = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            currentUser.setName(pharmacyAdmin.getName());
            currentUser.setSurname(pharmacyAdmin.getSurname());
            currentUser.setAddress(pharmacyAdmin.getAddress());
            currentUser.setCity(pharmacyAdmin.getCity());
            currentUser.setCountry(pharmacyAdmin.getCountry());
            currentUser.setPhoneNumber(pharmacyAdmin.getPhoneNumber());
            userRepository.save(currentUser);
        } else {
            message = "Update unsuccessfull!";
        }
        return message;
    }
}
