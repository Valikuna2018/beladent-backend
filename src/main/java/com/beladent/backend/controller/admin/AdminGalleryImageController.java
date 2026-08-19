package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.GalleryImageRequest;
import com.beladent.backend.dto.GalleryImageResponse;
import com.beladent.backend.service.GalleryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/gallery")
@RequiredArgsConstructor
public class AdminGalleryImageController {

    private final GalleryImageService galleryImageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GalleryImageResponse create(
            @RequestParam(required = false) String titleKa,
            @RequestParam(required = false) String titleEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        GalleryImageRequest request = new GalleryImageRequest();
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return galleryImageService.create(request, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GalleryImageResponse update(
            @PathVariable Long id,
            @RequestParam(required = false) String titleKa,
            @RequestParam(required = false) String titleEn,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        GalleryImageRequest request = new GalleryImageRequest();
        request.setTitleKa(titleKa);
        request.setTitleEn(titleEn);
        request.setSortOrder(sortOrder);
        request.setActive(active);

        return galleryImageService.update(id, request, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        galleryImageService.delete(id);
    }

    @GetMapping
    public List<GalleryImageResponse> getAll() {
        return galleryImageService.getAllForAdmin();
    }

    @GetMapping("/{id}")
    public GalleryImageResponse getById(@PathVariable Long id) {
        return galleryImageService.getById(id);
    }
}