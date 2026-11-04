package com.beladent.backend.service;

import com.beladent.backend.dto.ServiceRequest;
import com.beladent.backend.dto.ServiceResponse;
import com.beladent.backend.model.Service;
import com.beladent.backend.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final FileStorageService fileStorageService;


    public ServiceResponse create(ServiceRequest request) {
        if (serviceRepository.existsBySlug(request.getSlug())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service slug already exists");
        }

        Service service = Service.builder()
                .slug(request.getSlug())
                .titleKa(request.getTitleKa())
                .titleEn(request.getTitleEn())
                .shortDescriptionKa(request.getShortDescriptionKa())
                .shortDescriptionEn(request.getShortDescriptionEn())
                .descriptionKa(request.getDescriptionKa())
                .descriptionEn(request.getDescriptionEn())
                .seoTitleKa(request.getSeoTitleKa()).seoTitleEn(request.getSeoTitleEn())
                .metaDescriptionKa(request.getMetaDescriptionKa()).metaDescriptionEn(request.getMetaDescriptionEn())
                .imageUrl(request.getImageUrl())
                .imageAltKa(request.getImageAltKa())
                .imageAltEn(request.getImageAltEn())
                .sortOrder(request.getSortOrder())
                .active(request.getActive())
                .build();

        return mapToResponse(serviceRepository.save(service));
    }

    public ServiceResponse update(Long id, ServiceRequest request) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));

        if (!service.getSlug().equals(request.getSlug()) && serviceRepository.existsBySlug(request.getSlug())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service slug already exists");
        }

        service.setSlug(request.getSlug());
        service.setTitleKa(request.getTitleKa());
        service.setTitleEn(request.getTitleEn());
        service.setShortDescriptionKa(request.getShortDescriptionKa());
        service.setShortDescriptionEn(request.getShortDescriptionEn());
        service.setDescriptionKa(request.getDescriptionKa());
        service.setDescriptionEn(request.getDescriptionEn());
        service.setSeoTitleKa(request.getSeoTitleKa());
        service.setSeoTitleEn(request.getSeoTitleEn());
        service.setMetaDescriptionKa(request.getMetaDescriptionKa());
        service.setMetaDescriptionEn(request.getMetaDescriptionEn());
        service.setImageUrl(request.getImageUrl());
        service.setImageAltKa(request.getImageAltKa());
        service.setImageAltEn(request.getImageAltEn());
        service.setSortOrder(request.getSortOrder());
        service.setActive(request.getActive());

        return mapToResponse(serviceRepository.save(service));
    }

    public void delete(Long id) {

        Service service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Service not found"
                        ));

        if (service.getImageUrl() != null &&
                !service.getImageUrl().isBlank()) {

            fileStorageService.deleteFile(
                    service.getImageUrl()
            );
        }

        serviceRepository.delete(service);
    }

    public ServiceResponse getById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));

        return mapToResponse(service);
    }

    public ServiceResponse getBySlug(String slug) {
        Service service = serviceRepository.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));

        return mapToResponse(service);
    }

    public List<ServiceResponse> getAllForAdmin() {
        return serviceRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ServiceResponse> getAllActiveForPublic() {
        return serviceRepository.findByActiveTrueOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ServiceResponse mapToResponse(Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .slug(service.getSlug())
                .titleKa(service.getTitleKa())
                .titleEn(service.getTitleEn())
                .shortDescriptionKa(service.getShortDescriptionKa())
                .shortDescriptionEn(service.getShortDescriptionEn())
                .descriptionKa(service.getDescriptionKa())
                .descriptionEn(service.getDescriptionEn())
                .seoTitleKa(service.getSeoTitleKa()).seoTitleEn(service.getSeoTitleEn())
                .metaDescriptionKa(service.getMetaDescriptionKa()).metaDescriptionEn(service.getMetaDescriptionEn())
                .imageUrl(service.getImageUrl())
                .imageAltKa(service.getImageAltKa())
                .imageAltEn(service.getImageAltEn())
                .sortOrder(service.getSortOrder())
                .active(service.getActive())
                .createdAt(service.getCreatedAt())
                .updatedAt(service.getUpdatedAt())
                .build();
    }

    public ServiceResponse createWithImage(ServiceRequest request, MultipartFile file) {
        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            imageUrl = fileStorageService.saveFile(file, "services");
        }

        request.setImageUrl(imageUrl);

        return create(request);
    }

    public ServiceResponse updateWithImage(Long id, ServiceRequest request, MultipartFile file) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));

        String oldImageUrl = existingService.getImageUrl();

        if (file != null && !file.isEmpty()) {
            String newImageUrl = fileStorageService.saveFile(file, "services");
            request.setImageUrl(newImageUrl);

            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldImageUrl);
            }
        } else {
            request.setImageUrl(oldImageUrl);
        }

        return update(id, request);
    }
}
