package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.VacancyRequest;
import com.beladent.backend.dto.VacancyResponse;
import com.beladent.backend.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/vacancies")
@RequiredArgsConstructor
public class AdminVacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public VacancyResponse create(@Valid @RequestBody VacancyRequest request) {
        return vacancyService.create(request);
    }

    @PutMapping("/{id}")
    public VacancyResponse update(
            @PathVariable Long id,
            @Valid @RequestBody VacancyRequest request
    ) {
        return vacancyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vacancyService.delete(id);
    }

    @GetMapping
    public List<VacancyResponse> getAll() {
        return vacancyService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public VacancyResponse getById(@PathVariable Long id) {
        return vacancyService.getById(id);
    }
}