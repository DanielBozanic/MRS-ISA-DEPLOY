package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.dto.ConfirmationToken;

@Repository
public interface  ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	
	ConfirmationToken findByConfirmationToken(String confirmationToken);

}
