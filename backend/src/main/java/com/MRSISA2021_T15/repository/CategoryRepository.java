package com.MRSISA2021_T15.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
	Category findByCategoryName(CategoryName categoryName);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select c from Category c where c.categoryName = ?1")
	Category findByCategoryNamePessimisticWrite(CategoryName categoryName);

}
