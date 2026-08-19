package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.GalleryImageResponse;
import com.beladent.backend.service.GalleryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/gallery")
@RequiredArgsConstructor
public class PublicGalleryImageController {

    private final GalleryImageService galleryImageService;

    @GetMapping
    public List<GalleryImageResponse> getAllActive() {
        return galleryImageService.getAllActiveForPublic();
    }
}