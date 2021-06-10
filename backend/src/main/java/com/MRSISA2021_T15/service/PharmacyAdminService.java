package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.PharmacyAdmin;

public interface PharmacyAdminService {

    PharmacyAdmin getPharmacyAdminData();

    String updatePassword(ChangePassword passwords);

    String updatePharmacyAdminData(PharmacyAdmin pharmacyAdmin);

}
