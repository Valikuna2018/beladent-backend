package com.beladent.backend.repository;
import com.beladent.backend.model.SmileGalleryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SmileGalleryRepository extends JpaRepository<SmileGalleryItem,Long>{
    List<SmileGalleryItem> findByActiveTrueOrderBySortOrderAsc();
    List<SmileGalleryItem> findAllByOrderBySortOrderAsc();
}
