package com.MRSISA2021_T15.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.MRSISA2021_T15.dto.PurchaseOrderSupplierDTO;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.PurchaseOrderSupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SupplierServiceTest {
	
	@Mock
	private PurchaseOrderSupplierRepository purchaseOrderSupplierRepositoryMock;
	
	@InjectMocks
	private SupplierServiceImpl supplierService;
	
	@Test
	void writeOfferTest() {
		var order = new PurchaseOrder();
		order.setId(1);
		
		var offer = new PurchaseOrderSupplier();
		offer.setPurchaseOrder(order);
		
		var offerDto = new PurchaseOrderSupplierDTO();
		offerDto.setPurchaseOrder(order);
		
		var supplier = new Supplier();
		supplier.setId(10);
		
		when(purchaseOrderSupplierRepositoryMock.findBySupplierIdAndPurchaseOrderId(offer.getPurchaseOrder().getId(), supplier.getId()))
        	.thenReturn(offer);

		var message = supplierService.writeOfferToDatabase(offerDto, supplier);

		assertEquals("Supplier has already given an offer for this order!", message);

		verify(purchaseOrderSupplierRepositoryMock, times(1)).findBySupplierIdAndPurchaseOrderId(offer.getPurchaseOrder().getId(), supplier.getId());
	}

}
