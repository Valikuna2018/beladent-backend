package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.ServiceRequest;
import com.beladent.backend.dto.ServiceResponse;
import com.beladent.backend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/services")
@RequiredArgsConstructor
public class AdminServiceController {

    private final ServiceService serviceService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceResponse create(
            @RequestParam String slug,
            @RequestParam String titleKa,
            @RequestParam String titleEn,
            @RequestParam(required = false) String descriptionKa,
            @RequestParam(required = false) String descriptionEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        ServiceRequest request = new ServiceRequest();
        request.setSlug(slug);
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setDescriptionKa(descriptionKa);
        request.setDescriptionEn(descriptionEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return serviceService.createWithImage(request, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceResponse update(
            @PathVariable Long id,
            @RequestParam String slug,
            @RequestParam String titleKa,
            @RequestParam String titleEn,
            @RequestParam(required = false) String descriptionKa,
            @RequestParam(required = false) String descriptionEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        ServiceRequest request = new ServiceRequest();
        request.setSlug(slug);
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setDescriptionKa(descriptionKa);
        request.setDescriptionEn(descriptionEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return serviceService.updateWithImage(id, request, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceService.delete(id);
    }

    @GetMapping
    public List<ServiceResponse> getAll() {
        return serviceService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable Long id) {
        return serviceService.getById(id);
    }
}