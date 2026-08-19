package com.beladent.backend.service;

import com.beladent.backend.dto.AboutRequest;
import com.beladent.backend.dto.AboutResponse;
import com.beladent.backend.model.About;
import com.beladent.backend.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;
    private final FileStorageService fileStorageService;

    public AboutResponse getAbout() {
        About about = getOrCreateAbout();
        return mapToResponse(about);
    }

    public AboutResponse update(AboutRequest request, MultipartFile file) {
        About about = getOrCreateAbout();

        about.setTitleKa(request.getTitleKa());
        about.setTitleEn(request.getTitleEn());
        about.setDescriptionKa(request.getDescriptionKa());
        about.setDescriptionEn(request.getDescriptionEn());

        if (file != null && !file.isEmpty()) {
            String oldImageUrl = about.getImageUrl();
            String newImageUrl = fileStorageService.saveFile(file, "about");
            about.setImageUrl(newImageUrl);
            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldImageUrl);
            }
        }

        return mapToResponse(aboutRepository.save(about));
    }

    private About getOrCreateAbout() {
        return aboutRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> aboutRepository.save(About.builder().build()));
    }

    private AboutResponse mapToResponse(About about) {
        return AboutResponse.builder()
                .id(about.getId())
                .titleKa(about.getTitleKa())
                .titleEn(about.getTitleEn())
                .descriptionKa(about.getDescriptionKa())
                .descriptionEn(about.getDescriptionEn())
                .imageUrl(about.getImageUrl())
                .build();
    }
}
