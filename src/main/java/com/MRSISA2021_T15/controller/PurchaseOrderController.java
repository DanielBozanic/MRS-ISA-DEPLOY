package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.repository.PurchaseOrderMedicineRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    public PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    public PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;

    @RequestMapping(value = "/activePurchaseOrders")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrder> getActivePurchaseOrders(){
        ArrayList<PurchaseOrder> returnList = new ArrayList<>();
        for(PurchaseOrder po: purchaseOrderRepository.findAll()){
            if(LocalDate.now().isBefore(po.getDueDateOffer()) || LocalDate.now().isEqual(po.getDueDateOffer())){
                returnList.add(po);
            }
        }

    return returnList;
    }

    @RequestMapping(value = "/{purchaseOrderId}/getPurchaseOrder")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrderMedicine> getPurchaseOrder(@PathVariable Integer purchaseOrderId){
        ArrayList<PurchaseOrderMedicine> returnList = new ArrayList<>();
        for(PurchaseOrderMedicine pom: purchaseOrderMedicineRepository.findAll())
            if(pom.getPurchaseOrder().getId() == purchaseOrderId)
                returnList.add(pom);
        return returnList;
    }
}
