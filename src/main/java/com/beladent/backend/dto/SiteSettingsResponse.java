package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SiteSettingsResponse {

    private Long id;

    private String clinicNameKa;
    private String clinicNameEn;

    private String logoUrl;
    private String geoLogoUrl;
    private String backgroundImageUrl;
    private String backgroundImageUrl2;
    private String backgroundImageUrl3;

    private String seoTitleKa;
    private String seoTitleEn;

    private String seoDescriptionKa;
    private String seoDescriptionEn;
    private String servicesSubtitleKa;
    private String servicesSubtitleEn;
    private String doctorsSubtitleKa;
    private String doctorsSubtitleEn;
    private String newsSubtitleKa;
    private String newsSubtitleEn;
    private String vacanciesSubtitleKa;
    private String vacanciesSubtitleEn;
    private String smileGallerySubtitleKa;
    private String smileGallerySubtitleEn;
}
