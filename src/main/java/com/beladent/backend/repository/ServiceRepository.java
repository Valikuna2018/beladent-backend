package com.beladent.backend.repository;

import com.beladent.backend.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByActiveTrueOrderBySortOrderAsc();

    List<Service> findAllByOrderBySortOrderAsc();

    Optional<Service> findBySlug(String slug);

    boolean existsBySlug(String slug);
}