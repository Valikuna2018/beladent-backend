package com.beladent.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gallery_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String imageAltKa;
    private String imageAltEn;

    private String titleKa;
    private String titleEn;

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
