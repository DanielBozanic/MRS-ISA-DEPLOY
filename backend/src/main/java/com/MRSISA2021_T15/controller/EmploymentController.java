package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employment")
public class EmploymentController {

    @Autowired
    private EmploymentRepository employmentRepository;

    @GetMapping(path="/getPharmacyEmployees/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Employment> getPharmacyEmployees(@PathVariable Integer pharmacyId) {
        List<Employment> employmentList = employmentRepository.findAll();
        List returnList = new ArrayList<Employment>();
        for (Employment e : employmentList) {
            if (e.getPharmacy().getId() == pharmacyId) {
                returnList.add(e);
            }
        }
        return returnList;
    }


}
