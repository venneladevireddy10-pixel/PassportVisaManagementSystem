
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
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.VisaService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Passport;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Visa;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.VisaApplicationCost;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.PassportRepository;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.VisaApplicationCostRepository;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.VisaRepository;

@Service
public class VisaServiceImpl implements VisaService {

    @Autowired
    private VisaRepository visaRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private VisaApplicationCostRepository costRepository;
    @Override
    public Visa applyVisa(Visa visa) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated");
        }
        String loggedInUserId = authentication.getName();
        if (visa.getDateOfApplication() == null) {
            throw new InvalidDataException("Date Of Application is mandatory");
        }
        if (visa.getOccupation() == null || visa.getOccupation().isBlank()) {
            throw new InvalidDataException("Occupation is mandatory");
        }
        Passport passport = passportRepository.findById(visa.getPassportId()).orElse(null);
        if (passport == null) {
            throw new ResourceNotFoundException("Passport not found with id : " + visa.getPassportId());
        }
        if (!loggedInUserId.equals(passport.getUserId())) {
            throw new AccessDeniedException("You cannot apply for a visa using another user's passport");
        }
        if (visa.getDateOfApplication().isBefore(LocalDate.now())) {
            throw new InvalidDataException("Date Of Application cannot be before current date");
        }
        int nextNumber = visaRepository.findAll().stream().map(Visa::getVisaId).mapToInt(id -> {
                    String num = id.replaceAll("\\D", "");
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                })
                .max()
                .orElse(0) + 1;
        if (visa.getOccupation().equalsIgnoreCase("Student")) {
            visa.setVisaId("STU-" + nextNumber);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Private Employee")) {
            visa.setVisaId("PE-" + nextNumber);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Government Employee")) {
            visa.setVisaId("GE-" + nextNumber);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Self Employed")) {
            visa.setVisaId("SE-" + nextNumber);
        }
        else {
            visa.setVisaId("RE-" + nextNumber);
        }

        visa.setIssueDate(visa.getDateOfApplication().plusDays(10));
        LocalDate expiryDate = visa.getIssueDate();
        if (visa.getOccupation().equalsIgnoreCase("Student")) {
            expiryDate = expiryDate.plusYears(2);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Private Employee")) {
            expiryDate = expiryDate.plusYears(3);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Government Employee")) {
            expiryDate = expiryDate.plusYears(4);
        }
        else if (visa.getOccupation().equalsIgnoreCase("Self Employed")) {
            expiryDate = expiryDate.plusYears(1);
        }
        else {
            expiryDate = expiryDate.plusMonths(18);
        }
        if (passport.getExpiryDate() != null && expiryDate.isAfter(passport.getExpiryDate())) {
            expiryDate = passport.getExpiryDate();
        }

        visa.setExpiryDate(expiryDate);
        VisaApplicationCost cost = costRepository.findByOccupationAndPlace(visa.getOccupation(),visa.getCountry());
        if (cost != null) {
            visa.setRegistrationCost(cost.getPrice());
        }
        visa.setStatus("ACTIVE");
        return visaRepository.save(visa);
    }
    @Override
    public Visa getVisaById(String visaId) {
        Optional<Visa> v = visaRepository.findById(visaId);
        if (v.isPresent()) {
            return v.get();
        }
        throw new ResourceNotFoundException("Visa not found with id : " + visaId);
    }
    @Override
    public List<Visa> getAllVisas() {
        return visaRepository.findAll();
    }
    @Override
    public Visa updateVisa(Visa visa) {
        Optional<Visa> v = visaRepository.findById(visa.getVisaId());
        if (v.isPresent()) {
            return visaRepository.save(visa);
        }
        throw new ResourceNotFoundException("Visa not found with id : " + visa.getVisaId());
    }
    @Override
    public void deleteVisa(String visaId) {
        Optional<Visa> v = visaRepository.findById(visaId);
        if (v.isPresent()) {
            visaRepository.delete(v.get());
        }
        else {
            throw new ResourceNotFoundException("Visa not found with id : " + visaId);
        }
    }
    @Override
    public Visa cancelVisa(String visaId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated");
        }
        String loggedInUserId = authentication.getName();
        Optional<Visa> v = visaRepository.findById(visaId);
        if (v.isPresent()) {
            Visa visa = v.get();
            Passport passport = passportRepository.findById(visa.getPassportId()).orElseThrow(() -> new ResourceNotFoundException("Passport not found with id : "+ visa.getPassportId()));
            if (!loggedInUserId.equals(passport.getUserId())) {
                throw new AccessDeniedException("You cannot cancel another user's visa");
            }
            visa.setStatus("CANCELLED");
            return visaRepository.save(visa);
        }
        throw new ResourceNotFoundException("Visa not found with id : " + visaId);
    }
}
