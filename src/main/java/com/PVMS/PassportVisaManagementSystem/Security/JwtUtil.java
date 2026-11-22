package com.PVMS.PassportVisaManagementSystem.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "PVMSSecretKeyForJwtAuthenticationPVMS2026";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String userId) {
        return Jwts.builder().subject(userId).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(KEY).compact();
    }
    public String extractUserId(
            String token) {
        Claims claims = Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}