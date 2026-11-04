package com.beladent.backend.service;

import com.beladent.backend.dto.PartnerCompanyRequest;
import com.beladent.backend.dto.PartnerCompanyResponse;
import com.beladent.backend.model.PartnerCompany;
import com.beladent.backend.repository.PartnerCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service @RequiredArgsConstructor
public class PartnerCompanyService {
    private final PartnerCompanyRepository repository;
    private final FileStorageService fileStorageService;

    public PartnerCompanyResponse create(PartnerCompanyRequest request, MultipartFile file) {
        if (file == null || file.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Partner image is required");
        PartnerCompany partner = PartnerCompany.builder()
                .name(legacyName(request)).nameKa(blankToNull(request.getNameKa())).nameEn(blankToNull(request.getNameEn()))
                .imageUrl(fileStorageService.saveFile(file, "partners"))
                .sortOrder(request.getSortOrder()).active(request.getActive()).build();
        return map(repository.save(partner));
    }

    public PartnerCompanyResponse update(Long id, PartnerCompanyRequest request, MultipartFile file) {
        PartnerCompany partner = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partner not found"));
        partner.setName(legacyName(request));
        partner.setNameKa(blankToNull(request.getNameKa()));
        partner.setNameEn(blankToNull(request.getNameEn()));
        partner.setSortOrder(request.getSortOrder());
        partner.setActive(request.getActive());
        if (file != null && !file.isEmpty()) {
            String oldUrl = partner.getImageUrl();
            partner.setImageUrl(fileStorageService.saveFile(file, "partners"));
            if (oldUrl != null && !oldUrl.isBlank()) fileStorageService.deleteFile(oldUrl);
        }
        return map(repository.save(partner));
    }

    public void delete(Long id) {
        PartnerCompany partner = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partner not found"));
        if (partner.getImageUrl() != null && !partner.getImageUrl().isBlank()) fileStorageService.deleteFile(partner.getImageUrl());
        repository.delete(partner);
    }

    public PartnerCompanyResponse getById(Long id) {
        return map(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partner not found")));
    }
    public List<PartnerCompanyResponse> getAllForAdmin() { return repository.findAllByOrderBySortOrderAsc().stream().map(this::map).toList(); }
    public List<PartnerCompanyResponse> getAllActive() { return repository.findByActiveTrueOrderBySortOrderAsc().stream().map(this::map).toList(); }
    private String blankToNull(String value) { return value == null || value.isBlank() ? null : value.trim(); }
    private String legacyName(PartnerCompanyRequest request) {
        String legacy = blankToNull(request.getName());
        if (legacy != null) return legacy;
        String georgian = blankToNull(request.getNameKa());
        return georgian != null ? georgian : blankToNull(request.getNameEn());
    }
    private PartnerCompanyResponse map(PartnerCompany item) {
        return PartnerCompanyResponse.builder().id(item.getId()).name(item.getName())
                .nameKa(item.getNameKa() != null ? item.getNameKa() : item.getName())
                .nameEn(item.getNameEn() != null ? item.getNameEn() : item.getName())
                .imageUrl(item.getImageUrl())
                .sortOrder(item.getSortOrder()).active(item.getActive()).createdAt(item.getCreatedAt()).updatedAt(item.getUpdatedAt()).build();
    }
}
