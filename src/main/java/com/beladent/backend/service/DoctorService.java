package com.beladent.backend.service;

import com.beladent.backend.dto.DoctorRequest;
import com.beladent.backend.dto.DoctorResponse;
import com.beladent.backend.model.Doctor;
import com.beladent.backend.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final FileStorageService fileStorageService;

    public DoctorResponse createWithImage(DoctorRequest request, MultipartFile file) {
        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            imageUrl = fileStorageService.saveFile(file, "doctors");
        }

        request.setImageUrl(imageUrl);

        return create(request);
    }

    public DoctorResponse updateWithImage(Long id, DoctorRequest request, MultipartFile file) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        String oldImageUrl = existingDoctor.getImageUrl();

        if (file != null && !file.isEmpty()) {
            String newImageUrl = fileStorageService.saveFile(file, "doctors");
            request.setImageUrl(newImageUrl);

            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldImageUrl);
            }
        } else {
            request.setImageUrl(oldImageUrl);
        }

        return update(id, request);
    }

    public DoctorResponse create(DoctorRequest request) {
        if (doctorRepository.existsBySlug(request.getSlug())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor slug already exists");
        }

        Doctor doctor = Doctor.builder()
                .slug(request.getSlug())
                .firstNameKa(request.getFirstNameKa())
                .firstNameEn(request.getFirstNameEn())
                .lastNameKa(request.getLastNameKa())
                .lastNameEn(request.getLastNameEn())
                .specializationKa(request.getSpecializationKa())
                .specializationEn(request.getSpecializationEn())
                .descriptionKa(request.getDescriptionKa())
                .descriptionEn(request.getDescriptionEn())
                .imageUrl(request.getImageUrl())
                .imageAltKa(request.getImageAltKa())
                .imageAltEn(request.getImageAltEn())
                .sortOrder(request.getSortOrder())
                .active(request.getActive())
                .build();

        return mapToResponse(doctorRepository.save(doctor));
    }

    public DoctorResponse update(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        if (!doctor.getSlug().equals(request.getSlug()) && doctorRepository.existsBySlug(request.getSlug())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor slug already exists");
        }

        doctor.setSlug(request.getSlug());
        doctor.setFirstNameKa(request.getFirstNameKa());
        doctor.setFirstNameEn(request.getFirstNameEn());
        doctor.setLastNameKa(request.getLastNameKa());
        doctor.setLastNameEn(request.getLastNameEn());
        doctor.setSpecializationKa(request.getSpecializationKa());
        doctor.setSpecializationEn(request.getSpecializationEn());
        doctor.setDescriptionKa(request.getDescriptionKa());
        doctor.setDescriptionEn(request.getDescriptionEn());
        doctor.setImageUrl(request.getImageUrl());
        doctor.setImageAltKa(request.getImageAltKa());
        doctor.setImageAltEn(request.getImageAltEn());
        doctor.setSortOrder(request.getSortOrder());
        doctor.setActive(request.getActive());

        return mapToResponse(doctorRepository.save(doctor));
    }

    public void delete(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Doctor not found"
                        ));

        if (doctor.getImageUrl() != null &&
                !doctor.getImageUrl().isBlank()) {

            fileStorageService.deleteFile(
                    doctor.getImageUrl()
            );
        }

        doctorRepository.delete(doctor);
    }

    public DoctorResponse getById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        return mapToResponse(doctor);
    }

    public DoctorResponse getBySlug(String slug) {
        Doctor doctor = doctorRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        return mapToResponse(doctor);
    }

    public List<DoctorResponse> getAllForAdmin() {
        return doctorRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<DoctorResponse> getAllActiveForPublic() {
        return doctorRepository.findByActiveTrueOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .slug(doctor.getSlug())
                .firstNameKa(doctor.getFirstNameKa())
                .firstNameEn(doctor.getFirstNameEn())
                .lastNameKa(doctor.getLastNameKa())
                .lastNameEn(doctor.getLastNameEn())
                .specializationKa(doctor.getSpecializationKa())
                .specializationEn(doctor.getSpecializationEn())
                .descriptionKa(doctor.getDescriptionKa())
                .descriptionEn(doctor.getDescriptionEn())
                .imageUrl(doctor.getImageUrl())
                .imageAltKa(doctor.getImageAltKa())
                .imageAltEn(doctor.getImageAltEn())
                .sortOrder(doctor.getSortOrder())
                .active(doctor.getActive())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}
