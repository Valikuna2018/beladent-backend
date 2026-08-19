package com.beladent.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteSettingsRequest {

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
}
