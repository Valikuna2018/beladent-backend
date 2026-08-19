package com.beladent.backend.repository;

import com.beladent.backend.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByActiveTrueOrderByCreatedAtDesc();

    List<Vacancy> findAllByOrderByCreatedAtDesc();
}