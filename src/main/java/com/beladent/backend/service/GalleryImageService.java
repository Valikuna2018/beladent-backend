package com.beladent.backend.service;

import com.beladent.backend.dto.GalleryImageRequest;
import com.beladent.backend.dto.GalleryImageResponse;
import com.beladent.backend.model.GalleryImage;
import com.beladent.backend.repository.GalleryImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;
    private final FileStorageService fileStorageService;

    public GalleryImageResponse create(GalleryImageRequest request, MultipartFile file) {

        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            imageUrl = fileStorageService.saveFile(file, "gallery");
        }

        GalleryImage image = GalleryImage.builder()
                .titleKa(request.getTitleKa())
                .titleEn(request.getTitleEn())
                .imageUrl(imageUrl)
                .sortOrder(request.getSortOrder())
                .active(request.getActive())
                .build();

        return mapToResponse(galleryImageRepository.save(image));
    }

    public GalleryImageResponse update(Long id, GalleryImageRequest request, MultipartFile file) {

        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery image not found"));

        String oldImageUrl = image.getImageUrl();

        image.setTitleKa(request.getTitleKa());
        image.setTitleEn(request.getTitleEn());
        image.setSortOrder(request.getSortOrder());
        image.setActive(request.getActive());

        if (file != null && !file.isEmpty()) {
            String newImageUrl = fileStorageService.saveFile(file, "gallery");
            image.setImageUrl(newImageUrl);

            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldImageUrl);
            }
        }

        return mapToResponse(galleryImageRepository.save(image));
    }

    public void delete(Long id) {

        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery image not found"));

        if (image.getImageUrl() != null && !image.getImageUrl().isBlank()) {
            fileStorageService.deleteFile(image.getImageUrl());
        }

        galleryImageRepository.delete(image);
    }

    public GalleryImageResponse getById(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery image not found"));

        return mapToResponse(image);
    }

    public List<GalleryImageResponse> getAllForAdmin() {
        return galleryImageRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<GalleryImageResponse> getAllActiveForPublic() {
        return galleryImageRepository.findByActiveTrueOrderBySortOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private GalleryImageResponse mapToResponse(GalleryImage image) {
        return GalleryImageResponse.builder()
                .id(image.getId())
                .titleKa(image.getTitleKa())
                .titleEn(image.getTitleEn())
                .imageUrl(image.getImageUrl())
                .sortOrder(image.getSortOrder())
                .active(image.getActive())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .build();
    }
}