package com.beladent.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone1;
    private String phone2;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String addressKa;

    @Column(columnDefinition = "TEXT")
    private String addressEn;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String youtubeUrl;
    private String googleMapsUrl;

    private String mondaySaturdayOpen;
    private String mondaySaturdayClose;
    private String sundayOpen;
    private String sundayClose;

    @Builder.Default
    private Boolean sundayClosed = true;

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
