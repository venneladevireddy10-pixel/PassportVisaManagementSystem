package com.PVMS.PassportVisaManagementSystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PVMS.PassportVisaManagementSystem.Security.JwtUtil;
import com.PVMS.PassportVisaManagementSystem.Security.DTO.LoginRequest;
import com.PVMS.PassportVisaManagementSystem.Security.DTO.LoginResponse;

@RestController
@RequestMapping("/pvms/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserId(),request.getPassword()));
        String token = jwtUtil.generateToken( request.getUserId());
        return new LoginResponse(token);
    }
}