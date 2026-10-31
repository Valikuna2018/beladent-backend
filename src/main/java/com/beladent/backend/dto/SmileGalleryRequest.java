package com.beladent.backend.dto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class SmileGalleryRequest {
    private String titleKa;
    private String titleEn;
    private String textKa;
    private String textEn;
    private String imageAltKa;
    private String imageAltEn;
    private Integer sortOrder;
    private Boolean active;
}
