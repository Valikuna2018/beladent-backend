package com.beladent.backend.repository;

import com.beladent.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByActiveTrueOrderBySortOrderAsc();

    List<Doctor> findAllByOrderBySortOrderAsc();

    Optional<Doctor> findBySlug(String slug);

    boolean existsBySlug(String slug);
}