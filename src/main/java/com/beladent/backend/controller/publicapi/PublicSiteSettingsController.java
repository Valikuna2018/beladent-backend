package com.beladent.backend.controller.publicapi;

import com.beladent.backend.dto.SiteSettingsResponse;
import com.beladent.backend.service.SiteSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/site-settings")
@RequiredArgsConstructor
public class PublicSiteSettingsController {

    private final SiteSettingsService siteSettingsService;

    @GetMapping
    public SiteSettingsResponse getSettings() {
        return siteSettingsService.getSettings();
    }
}