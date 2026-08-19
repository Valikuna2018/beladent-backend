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

    @Size(max = 5000, message = "Georgian description is too long")
    private String descriptionKa;

    @Size(max = 5000, message = "English description is too long")
    private String descriptionEn;

    private String imageUrl;
    private Integer sortOrder;
    private Boolean active;
}