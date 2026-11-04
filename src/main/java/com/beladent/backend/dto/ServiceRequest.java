package com.beladent.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest {

    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "Georgian title is required")
    private String titleKa;

    @NotBlank(message = "English title is required")
    private String titleEn;

    @Size(max = 1000, message = "Georgian short description is too long")
    private String shortDescriptionKa;

    @Size(max = 1000, message = "English short description is too long")
    private String shortDescriptionEn;

    @Size(max = 5000, message = "Georgian description is too long")
    private String descriptionKa;

    @Size(max = 5000, message = "English description is too long")
    private String descriptionEn;

    @Size(max = 70) private String seoTitleKa;
    @Size(max = 70) private String seoTitleEn;
    @Size(max = 170) private String metaDescriptionKa;
    @Size(max = 170) private String metaDescriptionEn;

    private String imageUrl;
    @Size(max = 255, message = "Georgian image alt text is too long")
    private String imageAltKa;
    @Size(max = 255, message = "English image alt text is too long")
    private String imageAltEn;
    private Integer sortOrder;
    private Boolean active;
}
