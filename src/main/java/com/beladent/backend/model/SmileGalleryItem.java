package com.beladent.backend.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="smile_gallery_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SmileGalleryItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String titleKa;
    private String titleEn;
    @Column(columnDefinition="TEXT") private String textKa;
    @Column(columnDefinition="TEXT") private String textEn;
    private String imageUrl;
    private String imageAltKa;
    private String imageAltEn;
    private Integer sortOrder;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist public void onCreate(){createdAt=LocalDateTime.now();updatedAt=createdAt;if(sortOrder==null)sortOrder=0;if(active==null)active=true;}
    @PreUpdate public void onUpdate(){updatedAt=LocalDateTime.now();}
}
