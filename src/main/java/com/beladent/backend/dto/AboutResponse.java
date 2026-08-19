package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AboutResponse {

    private Long id;

    private String titleKa;
    private String titleEn;

    private String descriptionKa;
    private String descriptionEn;

    private String imageUrl;
}
