
package com.PVMS.PassportVisaManagementSystem.Models.BAO.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;

import com.PVMS.PassportVisaManagementSystem.Exceptions.InvalidDataException;
import com.PVMS.PassportVisaManagementSystem.Exceptions.ResourceNotFoundException;
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.PassportService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Passport;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.PassportRepository;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.RegistrationRepository;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.StatesRepository;

@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StatesRepository statesRepository;
    @Override
    public Passport applyPassport(Passport passport) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException( "User is not authenticated");
        }
        String loggedInUserId = authentication.getName();
        if (!loggedInUserId.equals(passport.getUserId())) { 
        	throw new AccessDeniedException("You cannot apply for another user's passport");
        }
        if (passport.getIssueDate() == null) {
            throw new InvalidDataException("Issue Date is mandatory");
        }
        if (passport.getIssueDate().isBefore(LocalDate.now())) {
            throw new InvalidDataException("Issue Date cannot be before current date");
        }
        if (!registrationRepository.existsById(passport.getUserId())) {
            throw new ResourceNotFoundException("User not found with id : " + passport.getUserId());
        }
        if (!statesRepository.existsById(passport.getStateId())) {
            throw new ResourceNotFoundException("State not found with id : " + passport.getStateId());
        }
        Passport lastPassport = passportRepository.findTopByOrderByPassportIdDesc();
        int nextNumber = 1;
        if (lastPassport != null) {
            String id = lastPassport.getPassportId();
            String num = id.substring(id.length() - 4);
            nextNumber = Integer.parseInt(num) + 1;
        }
        String prefix =  passport.getBookletType().equalsIgnoreCase("30 Pages")? "FPS-30" : "FPS-60";
        String newId = prefix + String.format("%04d", nextNumber);
        while (passportRepository.existsById(newId)) {
            nextNumber++;
            newId = prefix + String.format("%04d", nextNumber);
        }
        passport.setPassportId(newId);
        passport.setExpiryDate(passport.getIssueDate().plusYears(10));
        passport.setStatus("ACTIVE");
        return passportRepository.save(passport);
    }
    @Override
    public Passport getPassportById(String passportId) {
        Optional<Passport> p = passportRepository.findById(passportId);
        if (p.isPresent()) {
            return p.get();
        }
        throw new ResourceNotFoundException("Passport not found with id : " + passportId);
    }
    @Override
    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }
    @Override
    public Passport updatePassport(Passport passport) {
        Optional<Passport> p = passportRepository.findById(passport.getPassportId());
        if (p.isPresent()) {
            return passportRepository.save(passport);
        }
        throw new ResourceNotFoundException("Passport not found with id : " + passport.getPassportId());
    }
    @Override
    public void deletePassport(String passportId) {
        Optional<Passport> p = passportRepository.findById(passportId);
        if (p.isPresent()) {
            passportRepository.delete(p.get());
        }
        else {
            throw new ResourceNotFoundException("Passport not found with id : " + passportId);
        }
    }
    @Override
    public Passport reIssuePassport(String passportId,Passport passport) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException( "User is not authenticated");
        }
        String loggedInUserId = authentication.getName();
        Optional<Passport> p = passportRepository.findById(passportId);
        if (p.isPresent()) {
            Passport existingPassport = p.get();
            if (!loggedInUserId.equals(existingPassport.getUserId())) {
                throw new AccessDeniedException("You cannot renew another user's passport");
            }
            if (!statesRepository.existsById(passport.getStateId())) {
                throw new ResourceNotFoundException("State not found with id : " + passport.getStateId());
            }
            existingPassport.setCountry(passport.getCountry());
            existingPassport.setStateId(passport.getStateId());
            existingPassport.setCity(passport.getCity());
            existingPassport.setPincode(passport.getPincode());
            existingPassport.setBookletType(passport.getBookletType());
            existingPassport.setTypeOfService(passport.getTypeOfService());
            existingPassport.setIssueDate(passport.getIssueDate());
            existingPassport.setExpiryDate(passport.getIssueDate().plusYears(10));
            existingPassport.setStatus("REISSUED");
            return passportRepository.save(existingPassport);
        }
        throw new ResourceNotFoundException("Passport not found with id : " + passportId);
    }
}
