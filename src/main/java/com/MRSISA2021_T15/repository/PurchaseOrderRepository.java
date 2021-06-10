package com.MRSISA2021_T15.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer> {

	@Query("select order from PurchaseOrder order where order.dueDateOffer > ?1")
	List<PurchaseOrder> findOrdersByDueDateAfterCurrentDate(LocalDate currentDate);
}