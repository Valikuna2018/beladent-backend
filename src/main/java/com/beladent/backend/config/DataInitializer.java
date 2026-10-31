package com.beladent.backend.config;

import com.beladent.backend.model.AdminUser;
import com.beladent.backend.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {

        if (!adminUserRepository.existsByUsername(adminUsername)) {
            AdminUser admin = AdminUser.builder()
                    .username(adminUsername)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role("ROLE_ADMIN")
                    .active(true)
                    .build();

            adminUserRepository.save(admin);

            System.out.println("Initial administrator account created for username: " + adminUsername);
        } else {
            System.out.println("Initial administrator already exists.");
        }
    }
}
