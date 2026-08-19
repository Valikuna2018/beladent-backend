package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.DoctorResponse;
import com.beladent.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/doctors")
@RequiredArgsConstructor
public class PublicDoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorResponse> getAllActive() {
        return doctorService.getAllActiveForPublic();
    }

    @GetMapping("/{slug}")
    public DoctorResponse getBySlug(@PathVariable String slug) {
        return doctorService.getBySlug(slug);
    }
}