package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Passport;

public interface PassportService {
    Passport applyPassport(Passport passport);
    Passport getPassportById(String passportId);
    List<Passport> getAllPassports();
    Passport updatePassport(Passport passport);
    void deletePassport(String passportId);
    Passport reIssuePassport(String passportId, Passport passport);

}
