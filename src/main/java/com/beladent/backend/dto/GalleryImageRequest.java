package com.beladent.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryImageRequest {

    private String titleKa;
    private String titleEn;

    private String imageUrl;

    private Integer sortOrder;

    private Boolean active;
}