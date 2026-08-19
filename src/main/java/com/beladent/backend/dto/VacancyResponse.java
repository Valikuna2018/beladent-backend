package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class VacancyResponse {

    private Long id;

    private String titleKa;
    private String titleEn;

    private String positionKa;
    private String positionEn;

    private String descriptionKa;
    private String descriptionEn;

    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}