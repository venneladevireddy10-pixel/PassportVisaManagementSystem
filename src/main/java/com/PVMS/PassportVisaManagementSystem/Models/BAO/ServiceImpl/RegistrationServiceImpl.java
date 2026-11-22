package com.PVMS.PassportVisaManagementSystem.Models.BAO.ServiceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PVMS.PassportVisaManagementSystem.Exceptions.DuplicateResourceException;
import com.PVMS.PassportVisaManagementSystem.Exceptions.ResourceNotFoundException;
import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.RegistrationService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Registration;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Registration saveUser(Registration registration) {
        Registration emailExists = registrationRepository.findByEmail(registration.getEmail());
        if (emailExists != null) {
            throw new DuplicateResourceException("Email already exists");
        }
        int age =Period.between(registration.getDateOfBirth(), LocalDate.now()).getYears();
        if (age <= 1) {
            registration.setCitizenType("Infant");
        } else if (age <= 10) {
            registration.setCitizenType("Children");
        } else if (age <= 20) {
            registration.setCitizenType("Teen");
        } else if (age <= 50) {
            registration.setCitizenType("Adult");
        } else {
            registration.setCitizenType("Senior Citizen");
        }
        long count = registrationRepository.count();
        if (registration.getApplyType().equalsIgnoreCase("Passport")) {
            registration.setUserId("PASS-" + (1000 + count + 1));
        } else {
            registration.setUserId("VISA-" + (1000 + count + 1));
        }
        String[] symbols = {"@", "#", "$"};
        Random random = new Random();
        String password =LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMM")).toLowerCase()+ symbols[random.nextInt(3)] + (100 + random.nextInt(900));
        registration.setPassword(passwordEncoder.encode(password));
        registration.setStatus("ACTIVE");
        Registration saved = registrationRepository.save(registration);
        saved.setPassword(password);
        return saved;
    }
    @Override
    public Registration getUserById(String userId) {
        Optional<Registration> r = registrationRepository.findById(userId);
        if (r.isPresent()) {
            return r.get();
        }
        throw new ResourceNotFoundException("User not found with id : "+ userId);
    }
    @Override
    public List<Registration> getAllUsers() {
        return registrationRepository.findAll();
    }
    @Override
    public Registration updateUser(Registration registration) {
        Registration existing = registrationRepository.findById(registration.getUserId()).orElseThrow(() ->new ResourceNotFoundException( "User not found with id : "+ registration.getUserId()));
        // keep the stored BCrypt hash, because clients can't change it here
        registration.setPassword(existing.getPassword());
        return registrationRepository.save(registration);
    }
    @Override
    public void deleteUser(String userId) {
        Optional<Registration> r = registrationRepository.findById(userId);
        if (r.isPresent()) {
            registrationRepository.delete(r.get());
        }
        else {
            throw new ResourceNotFoundException("User not found with id : " + userId);
        }
    }
}