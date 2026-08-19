package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.ServiceResponse;
import com.beladent.backend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/services")
@RequiredArgsConstructor
public class PublicServiceController {

    private final ServiceService serviceService;

    @GetMapping
    public List<ServiceResponse> getAllActive() {
        return serviceService.getAllActiveForPublic();
    }

    @GetMapping("/{slug}")
    public ServiceResponse getBySlug(@PathVariable String slug) {
        return serviceService.getBySlug(slug);
    }
}