package com.beladent.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutRequest {

    @NotBlank(message = "Georgian title is required")
    private String titleKa;

    @NotBlank(message = "English title is required")
    private String titleEn;

    @Size(max = 10000, message = "Georgian description is too long")
    private String descriptionKa;

    @Size(max = 10000, message = "English description is too long")
    private String descriptionEn;

    private String imageUrl;
}
