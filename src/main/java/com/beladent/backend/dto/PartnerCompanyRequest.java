package com.beladent.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartnerCompanyRequest {
    private String name;
    private String nameKa;
    private String nameEn;
    private Integer sortOrder;
    private Boolean active;
}
