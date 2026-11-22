package com.PVMS.PassportVisaManagementSystem.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.PassportService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Passport;

@RestController
@RequestMapping("/pvms/passports")
public class PassportController {

    @Autowired
    private PassportService passportService;
    @PostMapping("/applyPassport")
    public Passport applyPassport(@RequestBody Passport passport) {
        return passportService.applyPassport(passport);
    }
    @GetMapping("/getPassportById/{passportId}")
    public Passport getPassportById(@PathVariable String passportId) {
        return passportService.getPassportById(passportId);
    }
    @GetMapping("/getAllPassports")
    public List<Passport> getAllPassports() {
        return passportService.getAllPassports();
    }
    @PutMapping("/updatePassport")
    public Passport updatePassport(@RequestBody Passport passport) {
        return passportService.updatePassport(passport);
    }
    @DeleteMapping("/deletePassport/{passportId}")
    public void deletePassport(@PathVariable String passportId) {
        passportService.deletePassport(passportId);
    }
    @PutMapping("/reIssuePassport/{passportId}")
    public Passport reIssuePassport(@PathVariable String passportId,@RequestBody Passport passport) {
        return passportService.reIssuePassport(passportId,passport);
    }
}