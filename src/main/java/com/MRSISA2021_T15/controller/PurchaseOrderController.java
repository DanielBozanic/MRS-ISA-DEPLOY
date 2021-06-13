package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.repository.PurchaseOrderMedicineRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    public PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    public PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;

    @GetMapping(value = "/activePurchaseOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public List<PurchaseOrder> getActivePurchaseOrders(){
        List<PurchaseOrder> returnList = new ArrayList<>();
        for(PurchaseOrder po: purchaseOrderRepository.findAll()){
            if(LocalDate.now().isBefore(po.getDueDateOffer()) || LocalDate.now().isEqual(po.getDueDateOffer())){
                returnList.add(po);
            }
        }

    return returnList;
    }

    @GetMapping(value = "/{purchaseOrderId}/getPurchaseOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public List<PurchaseOrderMedicine> getPurchaseOrder(@PathVariable Integer purchaseOrderId){
        List<PurchaseOrderMedicine> returnList = new ArrayList<>();
        for(PurchaseOrderMedicine pom: purchaseOrderMedicineRepository.findAll())
            if(pom.getPurchaseOrder().getId().equals(purchaseOrderId))
                returnList.add(pom);
        return returnList;
    }
}
