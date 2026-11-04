package com.beladent.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String titleKa;
    private String titleEn;

    @Column(columnDefinition = "TEXT")
    private String shortDescriptionKa;

    @Column(columnDefinition = "TEXT")
    private String shortDescriptionEn;

    @Column(columnDefinition = "TEXT")
    private String descriptionKa;

    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    private String seoTitleKa;
    private String seoTitleEn;
    @Column(columnDefinition = "TEXT") private String metaDescriptionKa;
    @Column(columnDefinition = "TEXT") private String metaDescriptionEn;

    private String imageUrl;
    private String imageAltKa;
    private String imageAltEn;

    private Integer sortOrder;

    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (active == null) {
            active = true;
        }

        if (sortOrder == null) {
            sortOrder = 0;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
