package com.example.Job.Portal.System.service.impl;

import com.example.Job.Portal.System.service.JwtService;
import com.example.Job.Portal.System.utils.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;

    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generateToken(String email) {
        return jwtUtil.generateToken(email);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails);
    }

    @Override
    public String extractEmail(String token) {
        return jwtUtil.extractEmail(token);
    }
}
