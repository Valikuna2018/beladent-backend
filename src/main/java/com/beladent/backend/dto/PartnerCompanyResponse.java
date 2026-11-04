package com.beladent.backend.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @Builder
public class PartnerCompanyResponse {
    private Long id;
    private String name;
    private String nameKa;
    private String nameEn;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
