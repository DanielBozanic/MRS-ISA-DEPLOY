package com.MRSISA2021_T15.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.JwtAuthenticationRequest;
import com.MRSISA2021_T15.dto.UserTokenState;
import com.MRSISA2021_T15.model.Role;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.model.User;
import com.MRSISA2021_T15.repository.RoleRepository;
import com.MRSISA2021_T15.repository.SystemAdminRepository;
import com.MRSISA2021_T15.utils.Constants;
import com.MRSISA2021_T15.utils.TokenUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SystemAdminRepository systemAdminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername().toLowerCase(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			User user = (User) authentication.getPrincipal();
			String jwt = tokenUtils.generateToken(user.getUsername());
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user.getId(), user.getUsername(), user.getRoles().get(0).getName(), user.getFirstLogin()));
		} catch (DisabledException de) {
			return new ResponseEntity<UserTokenState>(HttpStatus.FORBIDDEN);
		} catch (BadCredentialsException bdc) {
			return new ResponseEntity<UserTokenState>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/createFirstSystemAdmin")
	public void createFirstSystemAdmin() {
		List<SystemAdmin> systemAdmins = (List<SystemAdmin>) systemAdminRepository.findAll();
		if (systemAdmins.isEmpty()) {
			SystemAdmin systemAdmin = new SystemAdmin();
			systemAdmin.setUsername("admin");
			systemAdmin.setPassword(passwordEncoder.encode("ADMIN"));
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_SYSTEM_ADMIN).get();
			roles.add(role);
			systemAdmin.setRoles(roles);
			systemAdmin.setEnabled(true);
			systemAdminRepository.save(systemAdmin);
		}
	}
}
