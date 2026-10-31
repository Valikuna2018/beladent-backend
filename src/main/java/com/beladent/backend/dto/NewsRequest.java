package com.beladent.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {

    @NotBlank(message = "Georgian title is required")
    private String titleKa;

    @NotBlank(message = "English title is required")
    private String titleEn;

    @Size(max = 10000, message = "Georgian text is too long")
    private String textKa;

    @Size(max = 10000, message = "English text is too long")
    private String textEn;

    private String imageUrl;
    @Size(max = 255, message = "Georgian image alt text is too long")
    private String imageAltKa;
    @Size(max = 255, message = "English image alt text is too long")
    private String imageAltEn;

    private Integer sortOrder;

    private Boolean active;
}
