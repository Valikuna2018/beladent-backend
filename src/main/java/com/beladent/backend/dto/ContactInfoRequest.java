package com.beladent.backend.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInfoRequest {

    private String phone1;
    private String phone2;

    @Email(message = "Email is invalid")
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
    private String mondayFridayOpen;
    private String mondayFridayClose;
    private String saturdayOpen;
    private String saturdayClose;
    private boolean saturdayClosed;
    private String sundayOpen;
    private String sundayClose;
    private boolean sundayClosed;
}
