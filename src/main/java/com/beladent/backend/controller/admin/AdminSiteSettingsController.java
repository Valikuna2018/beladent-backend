package com.beladent.backend.controller.admin;

import com.beladent.backend.dto.SiteSettingsRequest;
import com.beladent.backend.dto.SiteSettingsResponse;
import com.beladent.backend.service.SiteSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/site-settings")
@RequiredArgsConstructor
public class AdminSiteSettingsController {

    private final SiteSettingsService siteSettingsService;

    @GetMapping
    public SiteSettingsResponse getSettings() {
        return siteSettingsService.getSettings();
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SiteSettingsResponse update(
            @RequestParam(required = false) String clinicNameKa,
            @RequestParam(required = false) String clinicNameEn,
            @RequestParam(required = false) String seoTitleKa,
            @RequestParam(required = false) String seoTitleEn,
            @RequestParam(required = false) String seoDescriptionKa,
            @RequestParam(required = false) String seoDescriptionEn,
            @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
            @RequestParam(value = "geoLogoFile", required = false) MultipartFile geoLogoFile,
            @RequestParam(value = "backgroundFile", required = false) MultipartFile backgroundFile,
            @RequestParam(value = "backgroundFile2", required = false) MultipartFile backgroundFile2,
            @RequestParam(value = "backgroundFile3", required = false) MultipartFile backgroundFile3
    ) {
        SiteSettingsRequest request = new SiteSettingsRequest();

        request.setClinicNameKa(clinicNameKa);
        request.setClinicNameEn(clinicNameEn);
        request.setSeoTitleKa(seoTitleKa);
        request.setSeoTitleEn(seoTitleEn);
        request.setSeoDescriptionKa(seoDescriptionKa);
        request.setSeoDescriptionEn(seoDescriptionEn);

        return siteSettingsService.updateWithImages(request, logoFile, geoLogoFile, backgroundFile, backgroundFile2, backgroundFile3);
    }
}
