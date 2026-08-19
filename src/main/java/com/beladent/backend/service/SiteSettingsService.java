package com.beladent.backend.service;

import com.beladent.backend.dto.SiteSettingsRequest;
import com.beladent.backend.dto.SiteSettingsResponse;
import com.beladent.backend.model.SiteSettings;
import com.beladent.backend.repository.SiteSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class SiteSettingsService {

    private final SiteSettingsRepository siteSettingsRepository;
    private final FileStorageService fileStorageService;

    public SiteSettingsResponse getSettings() {
        SiteSettings settings = getOrCreate();
        return mapToResponse(settings);
    }

    public SiteSettingsResponse updateWithImages(
            SiteSettingsRequest request,
            MultipartFile logoFile,
            MultipartFile geoLogoFile,
            MultipartFile backgroundFile,
            MultipartFile backgroundFile2,
            MultipartFile backgroundFile3
    ) {
        SiteSettings settings = getOrCreate();

        String oldLogoUrl = settings.getLogoUrl();
        String oldGeoLogoUrl = settings.getGeoLogoUrl();
        String oldBackgroundImageUrl = settings.getBackgroundImageUrl();
        String oldBackgroundImageUrl2 = settings.getBackgroundImageUrl2();
        String oldBackgroundImageUrl3 = settings.getBackgroundImageUrl3();

        settings.setClinicNameKa(request.getClinicNameKa());
        settings.setClinicNameEn(request.getClinicNameEn());

        settings.setSeoTitleKa(request.getSeoTitleKa());
        settings.setSeoTitleEn(request.getSeoTitleEn());

        settings.setSeoDescriptionKa(request.getSeoDescriptionKa());
        settings.setSeoDescriptionEn(request.getSeoDescriptionEn());

        if (logoFile != null && !logoFile.isEmpty()) {
            String newLogoUrl = fileStorageService.saveFile(logoFile, "logo");
            settings.setLogoUrl(newLogoUrl);

            if (oldLogoUrl != null && !oldLogoUrl.isBlank()) {
                fileStorageService.deleteFile(oldLogoUrl);
            }
        }

        if (backgroundFile != null && !backgroundFile.isEmpty()) {
            String newBackgroundUrl = fileStorageService.saveFile(backgroundFile, "background");
            settings.setBackgroundImageUrl(newBackgroundUrl);

            if (oldBackgroundImageUrl != null && !oldBackgroundImageUrl.isBlank()) {
                fileStorageService.deleteFile(oldBackgroundImageUrl);
            }
        }

        if (geoLogoFile != null && !geoLogoFile.isEmpty()) {
            String newGeoLogoUrl = fileStorageService.saveFile(geoLogoFile, "logo");
            settings.setGeoLogoUrl(newGeoLogoUrl);
            if (oldGeoLogoUrl != null && !oldGeoLogoUrl.isBlank()) {
                fileStorageService.deleteFile(oldGeoLogoUrl);
            }
        }

        if (backgroundFile2 != null && !backgroundFile2.isEmpty()) {
            String newBackgroundUrl = fileStorageService.saveFile(backgroundFile2, "background");
            settings.setBackgroundImageUrl2(newBackgroundUrl);
            if (oldBackgroundImageUrl2 != null && !oldBackgroundImageUrl2.isBlank()) {
                fileStorageService.deleteFile(oldBackgroundImageUrl2);
            }
        }

        if (backgroundFile3 != null && !backgroundFile3.isEmpty()) {
            String newBackgroundUrl = fileStorageService.saveFile(backgroundFile3, "background");
            settings.setBackgroundImageUrl3(newBackgroundUrl);
            if (oldBackgroundImageUrl3 != null && !oldBackgroundImageUrl3.isBlank()) {
                fileStorageService.deleteFile(oldBackgroundImageUrl3);
            }
        }

        return mapToResponse(siteSettingsRepository.save(settings));
    }

    private SiteSettings getOrCreate() {
        return siteSettingsRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> siteSettingsRepository.save(
                        SiteSettings.builder().build()
                ));
    }

    private SiteSettingsResponse mapToResponse(SiteSettings settings) {
        return SiteSettingsResponse.builder()
                .id(settings.getId())
                .clinicNameKa(settings.getClinicNameKa())
                .clinicNameEn(settings.getClinicNameEn())
                .logoUrl(settings.getLogoUrl())
                .geoLogoUrl(settings.getGeoLogoUrl())
                .backgroundImageUrl(settings.getBackgroundImageUrl())
                .backgroundImageUrl2(settings.getBackgroundImageUrl2())
                .backgroundImageUrl3(settings.getBackgroundImageUrl3())
                .seoTitleKa(settings.getSeoTitleKa())
                .seoTitleEn(settings.getSeoTitleEn())
                .seoDescriptionKa(settings.getSeoDescriptionKa())
                .seoDescriptionEn(settings.getSeoDescriptionEn())
                .build();
    }
}
