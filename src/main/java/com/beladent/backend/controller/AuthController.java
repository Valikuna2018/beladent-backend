package com.beladent.backend.controller;

import com.beladent.backend.dto.LoginRequest;
import com.beladent.backend.dto.LoginResponse;
import com.beladent.backend.model.AdminUser;
import com.beladent.backend.repository.AdminUserRepository;
import com.beladent.backend.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        AdminUser admin = adminUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!admin.isActive()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Admin user is inactive");
        }

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());

        return new LoginResponse(token, admin.getUsername(), admin.getRole());
    }
}