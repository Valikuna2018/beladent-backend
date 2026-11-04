package com.beladent.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequest {

    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "Georgian first name is required")
    private String firstNameKa;

    @NotBlank(message = "English first name is required")
    private String firstNameEn;

    @NotBlank(message = "Georgian last name is required")
    private String lastNameKa;

    @NotBlank(message = "English last name is required")
    private String lastNameEn;

    @Size(max = 255, message = "Georgian specialization is too long")
    private String specializationKa;

    @Size(max = 255, message = "English specialization is too long")
    private String specializationEn;

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
