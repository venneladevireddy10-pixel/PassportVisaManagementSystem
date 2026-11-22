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

import com.PVMS.PassportVisaManagementSystem.Models.BAO.Services.RegistrationService;
import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Registration;

@RestController
@RequestMapping("/pvms/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @PostMapping("/addUser")
    public Registration saveUser(@RequestBody Registration registration) {
        return registrationService.saveUser(registration);
    }
    @GetMapping("/getUserById/{userId}")
    public Registration getUserById(@PathVariable String userId) {
        return registrationService.getUserById(userId);
    }
    @GetMapping("/getAllUsers")
    public List<Registration> getAllUsers() {
        return registrationService.getAllUsers();
    }
    @PutMapping("/updateUser")
    public Registration updateUser(@RequestBody Registration registration) {
        return registrationService.updateUser(registration);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable String userId) {
        registrationService.deleteUser(userId);
    }
}