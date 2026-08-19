package com.beladent.backend.config;

import com.beladent.backend.model.AdminUser;
import com.beladent.backend.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String defaultUsername = "admin";
        String defaultEmail = "admin@beladent.com";
        String defaultPassword = "admin123";

        if (!adminUserRepository.existsByUsername(defaultUsername)) {
            AdminUser admin = AdminUser.builder()
                    .username(defaultUsername)
                    .email(defaultEmail)
                    .password(passwordEncoder.encode(defaultPassword))
                    .role("ROLE_ADMIN")
                    .active(true)
                    .build();

            adminUserRepository.save(admin);

            System.out.println("Default admin created:");
            System.out.println("Username: " + defaultUsername);
            System.out.println("Password: " + defaultPassword);
        } else {
            System.out.println("Default admin already exists.");
        }
    }
}