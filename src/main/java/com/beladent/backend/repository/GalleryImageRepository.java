package com.beladent.backend.repository;

import com.beladent.backend.model.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryImageRepository extends JpaRepository<GalleryImage, Long> {

    List<GalleryImage> findByActiveTrueOrderBySortOrderAsc();

    List<GalleryImage> findAllByOrderBySortOrderAsc();
}