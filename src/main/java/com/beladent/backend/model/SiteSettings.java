package com.beladent.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "site_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinicNameKa;
    private String clinicNameEn;

    private String logoUrl;
    private String geoLogoUrl;

    private String backgroundImageUrl;
    private String backgroundImageUrl2;
    private String backgroundImageUrl3;

    @Column(columnDefinition = "TEXT")
    private String seoTitleKa;

    @Column(columnDefinition = "TEXT")
    private String seoTitleEn;

    @Column(columnDefinition = "TEXT")
    private String seoDescriptionKa;

    @Column(columnDefinition = "TEXT")
    private String seoDescriptionEn;

    @Column(columnDefinition = "TEXT") private String servicesSubtitleKa;
    @Column(columnDefinition = "TEXT") private String servicesSubtitleEn;
    @Column(columnDefinition = "TEXT") private String doctorsSubtitleKa;
    @Column(columnDefinition = "TEXT") private String doctorsSubtitleEn;
    @Column(columnDefinition = "TEXT") private String newsSubtitleKa;
    @Column(columnDefinition = "TEXT") private String newsSubtitleEn;
    @Column(columnDefinition = "TEXT") private String vacanciesSubtitleKa;
    @Column(columnDefinition = "TEXT") private String vacanciesSubtitleEn;
    @Column(columnDefinition = "TEXT") private String smileGallerySubtitleKa;
    @Column(columnDefinition = "TEXT") private String smileGallerySubtitleEn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
