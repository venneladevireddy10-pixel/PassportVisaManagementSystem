package com.PVMS.PassportVisaManagementSystem.Models.BAO.Services;

import java.util.List;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Registration;

public interface RegistrationService {

    Registration saveUser(Registration registration);
    Registration getUserById(String userId);
    List<Registration> getAllUsers();
    Registration updateUser(Registration registration);
    void deleteUser(String userId);
    
    
}