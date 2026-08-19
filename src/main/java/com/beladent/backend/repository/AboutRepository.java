package com.beladent.backend.repository;

import com.beladent.backend.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About, Long> {
}