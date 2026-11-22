package com.PVMS.PassportVisaManagementSystem.Security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PVMS.PassportVisaManagementSystem.Models.Pojos.Registration;
import com.PVMS.PassportVisaManagementSystem.Models.Repositories.RegistrationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Registration registration = registrationRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new User(registration.getUserId(),registration.getPassword(),Collections.emptyList());
    }
}