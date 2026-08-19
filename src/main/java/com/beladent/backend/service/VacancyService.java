package com.beladent.backend.service;

import com.beladent.backend.dto.VacancyRequest;
import com.beladent.backend.dto.VacancyResponse;
import com.beladent.backend.model.Vacancy;
import com.beladent.backend.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public VacancyResponse create(VacancyRequest request) {
        Vacancy vacancy = Vacancy.builder()
                .titleKa(request.getTitleKa())
                .titleEn(request.getTitleEn())
                .positionKa(request.getPositionKa())
                .positionEn(request.getPositionEn())
                .descriptionKa(request.getDescriptionKa())
                .descriptionEn(request.getDescriptionEn())
                .active(request.getActive())
                .build();

        return mapToResponse(vacancyRepository.save(vacancy));
    }

    public VacancyResponse update(Long id, VacancyRequest request) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));

        vacancy.setTitleKa(request.getTitleKa());
        vacancy.setTitleEn(request.getTitleEn());
        vacancy.setPositionKa(request.getPositionKa());
        vacancy.setPositionEn(request.getPositionEn());
        vacancy.setDescriptionKa(request.getDescriptionKa());
        vacancy.setDescriptionEn(request.getDescriptionEn());
        vacancy.setActive(request.getActive());

        return mapToResponse(vacancyRepository.save(vacancy));
    }

    public void delete(Long id) {
        if (!vacancyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found");
        }

        vacancyRepository.deleteById(id);
    }

    public VacancyResponse getById(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacancy not found"));

        return mapToResponse(vacancy);
    }

    public List<VacancyResponse> getAllForAdmin() {
        return vacancyRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<VacancyResponse> getAllActiveForPublic() {
        return vacancyRepository.findByActiveTrueOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private VacancyResponse mapToResponse(Vacancy vacancy) {
        return VacancyResponse.builder()
                .id(vacancy.getId())
                .titleKa(vacancy.getTitleKa())
                .titleEn(vacancy.getTitleEn())
                .positionKa(vacancy.getPositionKa())
                .positionEn(vacancy.getPositionEn())
                .descriptionKa(vacancy.getDescriptionKa())
                .descriptionEn(vacancy.getDescriptionEn())
                .active(vacancy.getActive())
                .createdAt(vacancy.getCreatedAt())
                .updatedAt(vacancy.getUpdatedAt())
                .build();
    }
}