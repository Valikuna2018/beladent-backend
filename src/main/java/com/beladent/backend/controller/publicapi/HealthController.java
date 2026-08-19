package com.beladent.backend.controller.publicapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/public/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "beladent-backend",
                "timestamp", LocalDateTime.now()
        );
    }
}