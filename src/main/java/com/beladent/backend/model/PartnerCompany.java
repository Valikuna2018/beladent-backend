package com.beladent.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partner_companies")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PartnerCompany {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameKa;
    private String nameEn;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (active == null) active = true;
        if (sortOrder == null) sortOrder = 0;
    }

    @PreUpdate
    public void onUpdate() { updatedAt = LocalDateTime.now(); }
}
