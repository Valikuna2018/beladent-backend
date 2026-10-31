package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ServiceResponse {

    private Long id;
    private String slug;

    private String titleKa;
    private String titleEn;

    private String descriptionKa;
    private String descriptionEn;

    private String imageUrl;
    private String imageAltKa;
    private String imageAltEn;

    private Integer sortOrder;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
