package com.beladent.backend.repository;

import com.beladent.backend.model.PartnerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartnerCompanyRepository extends JpaRepository<PartnerCompany, Long> {
    List<PartnerCompany> findByActiveTrueOrderBySortOrderAsc();
    List<PartnerCompany> findAllByOrderBySortOrderAsc();
}
