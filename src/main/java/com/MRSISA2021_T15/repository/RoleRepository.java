package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
