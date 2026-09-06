package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactInfoResponse {

    private Long id;

    private String phone1;
    private String phone2;

    private String email;

    private String addressKa;
    private String addressEn;
    private String footerDescriptionKa;
    private String footerDescriptionEn;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String youtubeUrl;

    private String googleMapsUrl;
    private String mondaySaturdayOpen;
    private String mondaySaturdayClose;
    private String sundayOpen;
    private String sundayClose;
    private boolean sundayClosed;
}
