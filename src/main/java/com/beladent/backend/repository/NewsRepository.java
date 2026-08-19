package com.beladent.backend.repository;

import com.beladent.backend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByActiveTrueOrderBySortOrderAscCreatedAtDesc();

    List<News> findAllByOrderBySortOrderAscCreatedAtDesc();
}