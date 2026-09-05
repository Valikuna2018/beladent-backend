package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.ChangePasswordRequest;
import com.beladent.backend.model.AdminUser;
import com.beladent.backend.repository.AdminUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/account")
@RequiredArgsConstructor
public class AdminAccountController {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PutMapping("/password")
    public Map<String, String> changePassword(@Valid @RequestBody ChangePasswordRequest request, Authentication authentication) {
        AdminUser admin = adminUserRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin user not found"));
        if (!passwordEncoder.matches(request.currentPassword(), admin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
        }
        admin.setPassword(passwordEncoder.encode(request.newPassword()));
        adminUserRepository.save(admin);
        return Map.of("message", "Password changed successfully");
    }
}
