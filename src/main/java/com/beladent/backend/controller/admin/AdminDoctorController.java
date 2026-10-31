package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.DoctorRequest;
import com.beladent.backend.dto.DoctorResponse;
import com.beladent.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/doctors")
@RequiredArgsConstructor
public class AdminDoctorController {

    private final DoctorService doctorService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DoctorResponse create(
            @RequestParam String slug,
            @RequestParam String firstNameKa,
            @RequestParam String firstNameEn,
            @RequestParam String lastNameKa,
            @RequestParam String lastNameEn,
            @RequestParam(required = false) String specializationKa,
            @RequestParam(required = false) String specializationEn,
            @RequestParam(required = false) String descriptionKa,
            @RequestParam(required = false) String descriptionEn,
            @RequestParam(required = false) String imageAltKa,
            @RequestParam(required = false) String imageAltEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        DoctorRequest request = new DoctorRequest();
        request.setSlug(slug);
        request.setFirstNameKa(firstNameKa);
        request.setFirstNameEn(firstNameEn);
        request.setLastNameKa(lastNameKa);
        request.setLastNameEn(lastNameEn);
        request.setSpecializationKa(specializationKa);
        request.setSpecializationEn(specializationEn);
        request.setDescriptionKa(descriptionKa);
        request.setDescriptionEn(descriptionEn);
        request.setImageAltKa(imageAltKa);
        request.setImageAltEn(imageAltEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return doctorService.createWithImage(request, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DoctorResponse update(
            @PathVariable Long id,
            @RequestParam String slug,
            @RequestParam String firstNameKa,
            @RequestParam String firstNameEn,
            @RequestParam String lastNameKa,
            @RequestParam String lastNameEn,
            @RequestParam(required = false) String specializationKa,
            @RequestParam(required = false) String specializationEn,
            @RequestParam(required = false) String descriptionKa,
            @RequestParam(required = false) String descriptionEn,
            @RequestParam(required = false) String imageAltKa,
            @RequestParam(required = false) String imageAltEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        DoctorRequest request = new DoctorRequest();
        request.setSlug(slug);
        request.setFirstNameKa(firstNameKa);
        request.setFirstNameEn(firstNameEn);
        request.setLastNameKa(lastNameKa);
        request.setLastNameEn(lastNameEn);
        request.setSpecializationKa(specializationKa);
        request.setSpecializationEn(specializationEn);
        request.setDescriptionKa(descriptionKa);
        request.setDescriptionEn(descriptionEn);
        request.setImageAltKa(imageAltKa);
        request.setImageAltEn(imageAltEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return doctorService.updateWithImage(id, request, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }

    @GetMapping
    public List<DoctorResponse> getAll() {
        return doctorService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public DoctorResponse getById(@PathVariable Long id) {
        return doctorService.getById(id);
    }
}
