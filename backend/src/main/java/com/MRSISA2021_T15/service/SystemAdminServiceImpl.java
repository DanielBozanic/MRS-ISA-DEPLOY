package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		SystemAdmin currentUser = (SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SystemAdmin updatedSystemAdmin = (SystemAdmin) userRepository.findById(currentUser.getId()).get();
		if (!passwordEncoder.matches(passwords.getOldPassword(), updatedSystemAdmin.getPassword())) {
			message = "Wrong old password!";
		} else {
			if (updatedSystemAdmin.getFirstLogin()) {
				updatedSystemAdmin.setFirstLogin(false);
			}
			updatedSystemAdmin.setPassword(passwordEncoder.encode(passwords.getPassword()));
			userRepository.save(updatedSystemAdmin);
		}
		return message;
	}

}
