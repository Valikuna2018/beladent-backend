package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.VacancyResponse;
import com.beladent.backend.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/vacancies")
@RequiredArgsConstructor
public class PublicVacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyResponse> getAllActive() {
        return vacancyService.getAllActiveForPublic();
    }
}