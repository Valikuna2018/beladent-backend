package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DoctorResponse {

    private Long id;
    private String slug;

    private String firstNameKa;
    private String firstNameEn;

    private String lastNameKa;
    private String lastNameEn;

    private String specializationKa;
    private String specializationEn;

    private String descriptionKa;
    private String descriptionEn;

    private String imageUrl;

    private Integer sortOrder;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
