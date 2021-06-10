package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import com.MRSISA2021_T15.service.PharmacyAdminService;
import com.MRSISA2021_T15.service.PharmacyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/pharmacyAdmin")
public class PharmacyAdminController {

    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;
    @Autowired
    private PharmacyAdminService pharmacyAdminService;
    @Autowired
    private MedicinePharmacyRepository medicinePharmacyRepository;
    @Autowired
    private EmploymentPharmacistsRepository employmentPharmacistsRepository;
    @Autowired
    private EmploymentDermatologistRepository employmentDermatologistRepository;
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;

    @RequestMapping(path="/{pharmacyAdminId}/findById")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public Optional<PharmacyAdmin> getPharmacyAdminById(@PathVariable Integer pharmacyAdminId){
        return pharmacyAdminRepository.findById(pharmacyAdminId);
    }

    @PutMapping(value = "/updatePharmacyAdminData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> updatePharmacyAdminData(@RequestBody PharmacyAdmin pharmacyAdmin) {
        String message = pharmacyAdminService.updatePharmacyAdminData(pharmacyAdmin);
        Gson gson = new GsonBuilder().create();
        if (message.equals("")) {
            return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updatePharmacyData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> updatePharmacyData(@RequestBody Pharmacy pharmacy) {
        String message = pharmacyService.updatePharmacyData(pharmacy);
        Gson gson = new GsonBuilder().create();
        if (message.equals("")) {
            return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
        String message = pharmacyAdminService.updatePassword(passwords);
        Gson gson = new GsonBuilder().create();
        if (message.equals("")) {
            return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getPharmacyAdminData", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public PharmacyAdmin getPharmacyAdminData() {
        return pharmacyAdminService.getPharmacyAdminData();
    }

    @GetMapping(value = "/getPharmacyData", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public Pharmacy getPharmacyData() {
        return pharmacyService.getPharmacyData();
    }


    @PostMapping(value = "/addMedicineToPharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> addMedicineToPharmacy(@RequestBody MedicinePharmacy mp) {
        medicinePharmacyRepository.save(mp);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addPharmacistToPharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> addPharmacistToPharmacy(@RequestBody EmploymentPharmacist ep) {
        employmentPharmacistsRepository.save(ep);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addDermatologistToPharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> addDermatologistToPharmacy(@RequestBody EmploymentDermatologist ed) {
        employmentDermatologistRepository.save(ed);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/createPurchaseOrder")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto pod){
        PurchaseOrder po = new PurchaseOrder();
        po.setPharmacy(pod.getPharmacy());
        po.setDueDateOffer(pod.getPurchaseOrderDate().plusDays(1));
        po.setPurchaseOrderName(pod.getPurchaseOrderName());
        for (PurchaseOrderMedicine pom: pod.getPurchaseOrderMedicine())
        {
            po.getPurchaseOrderMedicine().add(pom);
            purchaseOrderMedicineRepository.save(pom);
        }
        purchaseOrderRepository.save(po);
        for (PurchaseOrderMedicine pom: po.getPurchaseOrderMedicine()) {
            pom.setPurchaseOrder(po);
            purchaseOrderMedicineRepository.save(pom);
        }

        return ResponseEntity.ok().build();
    }
}
