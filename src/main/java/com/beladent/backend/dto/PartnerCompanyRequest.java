package com.beladent.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PartnerCompanyRequest {
    private String name;
    private Integer sortOrder;
    private Boolean active;
}
