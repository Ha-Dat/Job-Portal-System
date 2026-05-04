package com.example.Job.Portal.System.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String email);

    boolean validateToken(String token, UserDetails userDetails);

    String extractEmail(String token);
}
